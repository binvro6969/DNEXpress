/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.An.DriverImplement;
import Impl.An.GeneralImplement;
import static google_context.JSONReader.fetchJSONFromURL;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Driver;
import model.Invoice;
import model.Order_Status;
import model.Package_Detail;
import model.Post_Office;
import model.Shipment_Order;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author haian
 */
public class Driver_ReceiveOrder_DetailOrder_Servlet extends HttpServlet {

    static DriverImplement driverImplement = new DriverImplement();
    static GeneralImplement generalImplement = new GeneralImplement();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        String apiKey = "AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0";

        String order_id = request.getParameter("id");

        boolean check_InProvince = driverImplement.checkInProvince(Integer.parseInt(order_id));
        if (check_InProvince == false) {
            //xử Lí Ngoại tỉnh
            Order_Status order_Status = driverImplement.getOrderStatus(Integer.parseInt(order_id));
            Order_Status order_Status_receiveOrder = driverImplement.getOrderStatusReceiveOrder(Integer.parseInt(order_id));
            //kiểm tra đơn hàng đang trong quá trình giao hay quá trình nhận
            if(order_Status_receiveOrder.getStatus()== null){
                //Trạng thái ship hàng
                if (order_Status.getStatus() == null) {
                    response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
                    return;
                } else {
                    
                    if (order_Status.getStatus().equals("Picking up")) {
                        //Nhận hàng từ người gửi
                        Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                        double origin_lat;
                        double origin_lng;
                        String pick_up_apartment_Number = shipment_Order.getPick_up_apartment_number();
                        String pick_up_street_Name = shipment_Order.getPick_up_street_name();
                        String pick_up_district = shipment_Order.getPick_up_district();
                        String pick_up_ward = shipment_Order.getPick_up_ward();
                        String pick_up_city = shipment_Order.getPick_up_city();
                        String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                        String final_street_Name = shipment_Order.getFinal_street_name();
                        String final_district = shipment_Order.getFinal_district();
                        String final_ward = shipment_Order.getFinal_ward();
                        String final_city = shipment_Order.getFinal_city();
                        String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                        String pick_up_address = pick_up_apartment_Number + "," + pick_up_street_Name + "," + pick_up_district + "," + pick_up_ward + "," + pick_up_city;
                        String pick_up_encodedAddress = URLEncoder.encode(pick_up_address, StandardCharsets.UTF_8.toString());
                        String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pick_up_encodedAddress + ";&key=" + apiKey;
                        double destination_lat = 0;
                        double destination_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    destination_lat = location.getDouble("lat");
                                    destination_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        HttpSession session = request.getSession();
                        Account account = (Account) session.getAttribute("loginSession");
                        if (account != null) {
                            int driver_id = account.getAccount_id();
                            Driver driver = driverImplement.findDriverLocation(driver_id);
                            origin_lat = driver.getLatitude();
                            origin_lng = driver.getLongitude();
                            Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                            int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                            Account accountInfor = generalImplement.getAccountinfor(cus_id);
                            String fname = accountInfor.getFirstName();
                            String lname = accountInfor.getLastName();
                            String phone = accountInfor.getPhone_numb();
                            String name = fname + " " + lname;
                            Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                            //lấy size
                            String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                            //lấy type
                            String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                            //lấy weight
                            float package_Weight = package_Detail.getPackage_weight();
                            request.setAttribute("package_Size_value", package_Size_value);
                            request.setAttribute("package_Type_value", package_Type_value);
                            request.setAttribute("package_Weight", package_Weight);
                            request.setAttribute("name", name);
                            request.setAttribute("phone", phone);
                            request.setAttribute("shipment_Order_infor", invoice_infor);
                            request.setAttribute("final_address", final_address);
                            request.setAttribute("destination_lat", destination_lat);
                            request.setAttribute("destination_lng", destination_lng);
                            request.setAttribute("origin_lat", origin_lat);
                            request.setAttribute("origin_lng", origin_lng);
                            request.setAttribute("key_API", apiKey);
                            request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                        }
                    }
                    if (order_Status.getStatus().equals("In Transit")) {
                        //Lấy địa chỉ của đơn hàng
                        Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                        //Lấy địa chỉ Post_office 
                        Post_Office post_Office = driverImplement.getPostOfficeFromStatus(Integer.parseInt(order_id));
                        //Lấy vị trí điểm đầu
                        //Vị trí của sender
                        double origin_lat = 0;
                        double origin_lng = 0;
                        String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                        String final_street_Name = shipment_Order.getFinal_street_name();
                        String final_district = shipment_Order.getFinal_district();
                        String final_ward = shipment_Order.getFinal_ward();
                        String final_city = shipment_Order.getFinal_city();
                        String pick_up_apartment_Number = shipment_Order.getPick_up_apartment_number();
                        String pick_up_street_Name = shipment_Order.getPick_up_street_name();
                        String pick_up_district = shipment_Order.getPick_up_district();
                        String pick_up_ward = shipment_Order.getPick_up_ward();
                        String pick_up_city = shipment_Order.getPick_up_city();
                        String pick_up_address = pick_up_apartment_Number + "," + pick_up_street_Name + "," + pick_up_district + "," + pick_up_ward + "," + pick_up_city;
                        String pick_up_encodedAddress = URLEncoder.encode(pick_up_address, StandardCharsets.UTF_8.toString());
                        String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pick_up_encodedAddress + ";&key=" + apiKey;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    origin_lat = location.getDouble("lat");
                                    origin_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        //lấy vị trí điểm cuối
                        //Vị trí của post_office(sender)
                        String destination_apartment_Number = post_Office.getApartmentNumber();
                        String destination_street_Name = post_Office.getStreetName();
                        String destination_district = post_Office.getDistrict();
                        String destination_ward = post_Office.getWard();
                        String destination_city = post_Office.getCity();
                        String destination_address = destination_apartment_Number + "," + destination_street_Name + "," + destination_district + "," + destination_ward + "," + destination_city;
                        String destination_encodedAddress = URLEncoder.encode(destination_address, StandardCharsets.UTF_8.toString());
                        //lấy toạ độ điểm cuối
                        String origin_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + destination_encodedAddress + ";&key=" + apiKey;
                        double destination_lat = 0;
                        double destination_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(origin_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    destination_lat = location.getDouble("lat");
                                    destination_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                        int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                        Account accountInfor = generalImplement.getAccountinfor(cus_id);
                        String fname = accountInfor.getFirstName();
                        String lname = accountInfor.getLastName();
                        String phone = accountInfor.getPhone_numb();
                        String name = fname + " " + lname;
                        Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                        //lấy size
                        String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                        //lấy type
                        String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                        //lấy weight
                        float package_Weight = package_Detail.getPackage_weight();
                        //lấy địa chỉ receiver
                        String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;

                        request.setAttribute("package_Size_value", package_Size_value);
                        request.setAttribute("package_Type_value", package_Type_value);
                        request.setAttribute("package_Weight", package_Weight);
                        request.setAttribute("name", name);
                        request.setAttribute("phone", phone);
                        request.setAttribute("shipment_Order_infor", invoice_infor);
                        request.setAttribute("final_address", final_address);
                        request.setAttribute("destination_lat", destination_lat);
                        request.setAttribute("destination_lng", destination_lng);
                        request.setAttribute("origin_lat", origin_lat);
                        request.setAttribute("origin_lng", origin_lng);
                        request.setAttribute("key_API", apiKey);
                        request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                    }
                    if (order_Status.getStatus().equals("In Transit-2")) {
                        //Xử lý hàng tại bưu cục                   
                        //Lấy địa chỉ của đơn hàng
                        Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                        //Lấy địa chỉ Post_office 
                        Post_Office post_Office = driverImplement.getPostOfficeFromStatus(Integer.parseInt(order_id));
                        //Xử lý tại post_office của receiver

                        String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                        String final_street_Name = shipment_Order.getFinal_street_name();
                        String final_district = shipment_Order.getFinal_district();
                        String final_ward = shipment_Order.getFinal_ward();
                        String final_city = shipment_Order.getFinal_city();
                        Post_Office post_Office_origin = driverImplement.getPostOfficeInTransit(Integer.parseInt(order_id));
                        String origin_apartment_Number = post_Office_origin.getApartmentNumber();
                        String origin_street_Name = post_Office_origin.getStreetName();
                        String origin_district = post_Office_origin.getDistrict();
                        String origin_ward = post_Office_origin.getWard();
                        String origin_city = post_Office_origin.getCity();
                        String origin_address = origin_apartment_Number + "," + origin_street_Name + "," + origin_district + "," + origin_ward + "," + origin_city;
                        String origin_encodedAddress = URLEncoder.encode(origin_address, StandardCharsets.UTF_8.toString());
                        //lấy toạ độ điểm đầu
                        String origin_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + origin_encodedAddress + ";&key=" + apiKey;
                        double origin_lat = 0;
                        double origin_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(origin_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    origin_lat = location.getDouble("lat");
                                    origin_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        //lấy toạ độ điểm cuối
                        String destination_city_check = shipment_Order.getFinal_city();
                        Post_Office post_Office_destination = driverImplement.getPostOfficeFromCity(destination_city_check);
                        String destination_apartment_Number = post_Office_destination.getApartmentNumber();
                        String destination_street_Name = post_Office_destination.getStreetName();
                        String destination_district = post_Office_destination.getDistrict();
                        String destination_ward = post_Office_destination.getWard();
                        String destination_city = post_Office_destination.getCity();
                        String destination_address = destination_apartment_Number + "," + destination_street_Name + "," + destination_district + "," + destination_ward + "," + destination_city;
                        String destination_encodedAddress = URLEncoder.encode(destination_address, StandardCharsets.UTF_8.toString());
                        String destination_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + destination_encodedAddress + ";&key=" + apiKey;
                        double destination_lat = 0;
                        double destination_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(destination_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    destination_lat = location.getDouble("lat");
                                    destination_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }

                        Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                        int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                        Account accountInfor = generalImplement.getAccountinfor(cus_id);
                        String fname = accountInfor.getFirstName();
                        String lname = accountInfor.getLastName();
                        String phone = accountInfor.getPhone_numb();
                        String name = fname + " " + lname;
                        Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                        //lấy size
                        String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                        //lấy type
                        String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                        //lấy weight
                        float package_Weight = package_Detail.getPackage_weight();
                        //lấy địa chỉ receiver
                        String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;

                        request.setAttribute("package_Size_value", package_Size_value);
                        request.setAttribute("package_Type_value", package_Type_value);
                        request.setAttribute("package_Weight", package_Weight);
                        request.setAttribute("name", name);
                        request.setAttribute("phone", phone);
                        request.setAttribute("shipment_Order_infor", invoice_infor);
                        request.setAttribute("final_address", final_address);
                        request.setAttribute("destination_lat", destination_lat);
                        request.setAttribute("destination_lng", destination_lng);
                        request.setAttribute("origin_lat", origin_lat);
                        request.setAttribute("origin_lng", origin_lng);
                        request.setAttribute("key_API", apiKey);
                        request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                    }
                }
                if (order_Status.getStatus().equals("Delivering")) {
                    Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                    String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                    String final_street_Name = shipment_Order.getFinal_street_name();
                    String final_district = shipment_Order.getFinal_district();
                    String final_ward = shipment_Order.getFinal_ward();
                    String final_city = shipment_Order.getFinal_city();
                    String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                    String final_encodedAddress = URLEncoder.encode(final_address, StandardCharsets.UTF_8.toString());
                    String final_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + final_encodedAddress + ";&key=" + apiKey;
                    double destination_lat = 0;
                    double destination_lng = 0;
                    try {
                        JSONObject jsonData = fetchJSONFromURL(final_urlString);
                        if (jsonData != null) {
                            JSONArray results = jsonData.getJSONArray("results");
                            if (results.length() > 0) {
                                JSONObject firstResult = results.getJSONObject(0);
                                JSONObject geometry = firstResult.getJSONObject("geometry");
                                JSONObject location = geometry.getJSONObject("location");
                                destination_lat = location.getDouble("lat");
                                destination_lng = location.getDouble("lng");
                            }
                        }
                    } catch (Exception e) {
                        Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                    }
                    HttpSession session = request.getSession();
                    Account account = (Account) session.getAttribute("loginSession");
                    if (account != null) {
                        int driver_id = account.getAccount_id();
                        Driver driver = driverImplement.findDriverLocation(driver_id);
                        float origin_lat = driver.getLatitude();
                        float origin_lng = driver.getLongitude();
                        Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                        int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                        Account accountInfor = generalImplement.getAccountinfor(cus_id);
                        String fname = accountInfor.getFirstName();
                        String lname = accountInfor.getLastName();
                        String phone = accountInfor.getPhone_numb();
                        String name = fname + " " + lname;
                        Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                        //lấy size
                        String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                        //lấy type
                        String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                        //lấy weight
                        float package_Weight = package_Detail.getPackage_weight();
                        request.setAttribute("package_Size_value", package_Size_value);
                        request.setAttribute("package_Type_value", package_Type_value);
                        request.setAttribute("package_Weight", package_Weight);
                        request.setAttribute("name", name);
                        request.setAttribute("phone", phone);
                        request.setAttribute("shipment_Order_infor", invoice_infor);
                        request.setAttribute("final_address", final_address);
                        request.setAttribute("destination_lat", destination_lat);
                        request.setAttribute("destination_lng", destination_lng);
                        request.setAttribute("origin_lat", origin_lat);
                        request.setAttribute("origin_lng", origin_lng);
                        request.setAttribute("key_API", apiKey);
                        request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                    }
                }
            }else{
                //Trạng thái nhận đơn của shipper
                Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                double origin_lat;
                double origin_lng;
                String pick_up_apartment_Number = shipment_Order.getPick_up_apartment_number();
                String pick_up_street_Name = shipment_Order.getPick_up_street_name();
                String pick_up_district = shipment_Order.getPick_up_district();
                String pick_up_ward = shipment_Order.getPick_up_ward();
                String pick_up_city = shipment_Order.getPick_up_city();
                
                String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                String final_street_Name = shipment_Order.getFinal_street_name();
                String final_district = shipment_Order.getFinal_district();
                String final_ward = shipment_Order.getFinal_ward();
                String final_city = shipment_Order.getFinal_city();
                String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                String pick_up_address = pick_up_apartment_Number + "," + pick_up_street_Name + "," + pick_up_district + "," + pick_up_ward + "," + pick_up_city;
                String pick_up_encodedAddress = URLEncoder.encode(pick_up_address, StandardCharsets.UTF_8.toString());
                String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pick_up_encodedAddress + ";&key=" + apiKey;
                double destination_lat = 0;
                double destination_lng = 0;
                try {
                    JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
                    if (jsonData != null) {
                        JSONArray results = jsonData.getJSONArray("results");
                        if (results.length() > 0) {
                            JSONObject firstResult = results.getJSONObject(0);
                            JSONObject geometry = firstResult.getJSONObject("geometry");
                            JSONObject location = geometry.getJSONObject("location");
                            destination_lat = location.getDouble("lat");
                            destination_lng = location.getDouble("lng");
                        }
                    }
                } catch (Exception e) {
                    Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                }
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("loginSession");
                if (account != null) {
                    int driver_id = account.getAccount_id();
                    Driver driver = driverImplement.findDriverLocation(driver_id);
                    origin_lat = driver.getLatitude();
                    origin_lng = driver.getLongitude();
                    Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                    int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                    Account accountInfor = generalImplement.getAccountinfor(cus_id);
                    String fname = accountInfor.getFirstName();
                    String lname = accountInfor.getLastName();
                    String phone = accountInfor.getPhone_numb();
                    String name = fname + " " + lname;
                    Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                    //lấy size
                    String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                    //lấy type
                    String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                    //lấy weight
                    float package_Weight = package_Detail.getPackage_weight();
                    request.setAttribute("package_Size_value", package_Size_value);
                    request.setAttribute("package_Type_value", package_Type_value);
                    request.setAttribute("package_Weight", package_Weight);
                    request.setAttribute("name", name);
                    request.setAttribute("phone", phone);
                    request.setAttribute("shipment_Order_infor", invoice_infor);
                    request.setAttribute("final_address", final_address);
                    request.setAttribute("pick_up_address", pick_up_address);
                    request.setAttribute("destination_lat", destination_lat);
                    request.setAttribute("destination_lng", destination_lng);
                    request.setAttribute("origin_lat", origin_lat);
                    request.setAttribute("origin_lng", origin_lng);
                    request.setAttribute("key_API", apiKey);
                    request.setAttribute("check_InProvince", check_InProvince);
                    request.getRequestDispatcher("DRV_ReceiveOrder_ReceiveOrder.jsp").forward(request, response);
                    return;
                }
            }                                                           
        } else {
            //xử Lí nội tỉnh
            Order_Status order_Status = driverImplement.getOrderStatus(Integer.parseInt(order_id));
            Order_Status order_Status_receiveOrder = driverImplement.getOrderStatusReceiveOrder(Integer.parseInt(order_id));
            //Trạng thái nhận đơn
            //check có trong trạng thái nhận đơn hay không
            if(order_Status_receiveOrder.getStatus()== null){
                //trạng thái giao hàng
                if (order_Status.getStatus() == null) {
                    response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
                    return;
                } else {
                    if (order_Status.getStatus().equals("Picking up")) {
                        //Nhận hàng từ người gửi
                        Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                        double origin_lat;
                        double origin_lng;
                        String pick_up_apartment_Number = shipment_Order.getPick_up_apartment_number();
                        String pick_up_street_Name = shipment_Order.getPick_up_street_name();
                        String pick_up_district = shipment_Order.getPick_up_district();
                        String pick_up_ward = shipment_Order.getPick_up_ward();
                        String pick_up_city = shipment_Order.getPick_up_city();
                        String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                        String final_street_Name = shipment_Order.getFinal_street_name();
                        String final_district = shipment_Order.getFinal_district();
                        String final_ward = shipment_Order.getFinal_ward();
                        String final_city = shipment_Order.getFinal_city();
                        String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                        String pick_up_address = pick_up_apartment_Number + "," + pick_up_street_Name + "," + pick_up_district + "," + pick_up_ward + "," + pick_up_city;
                        String pick_up_encodedAddress = URLEncoder.encode(pick_up_address, StandardCharsets.UTF_8.toString());
                        String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pick_up_encodedAddress + ";&key=" + apiKey;
                        double destination_lat = 0;
                        double destination_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    destination_lat = location.getDouble("lat");
                                    destination_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        HttpSession session = request.getSession();
                        Account account = (Account) session.getAttribute("loginSession");
                        if (account != null) {
                            int driver_id = account.getAccount_id();
                            Driver driver = driverImplement.findDriverLocation(driver_id);
                            origin_lat = driver.getLatitude();
                            origin_lng = driver.getLongitude();

                            Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                            int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                            Account accountInfor = generalImplement.getAccountinfor(cus_id);
                            String fname = accountInfor.getFirstName();
                            String lname = accountInfor.getLastName();
                            String phone = accountInfor.getPhone_numb();
                            String name = fname + " " + lname;
                            Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                            //lấy size
                            String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                            //lấy type
                            String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                            //lấy weight
                            float package_Weight = package_Detail.getPackage_weight();
                            request.setAttribute("package_Size_value", package_Size_value);
                            request.setAttribute("package_Type_value", package_Type_value);
                            request.setAttribute("package_Weight", package_Weight);
                            request.setAttribute("name", name);
                            request.setAttribute("phone", phone);
                            request.setAttribute("shipment_Order_infor", invoice_infor);
                            request.setAttribute("final_address", final_address);
                            request.setAttribute("destination_lat", destination_lat);
                            request.setAttribute("destination_lng", destination_lng);
                            request.setAttribute("origin_lat", origin_lat);
                            request.setAttribute("origin_lng", origin_lng);
                            request.setAttribute("key_API", apiKey);
                            request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                        }
                    }
                    if (order_Status.getStatus().equals("Delivering")) {
                        //Ship hàng tới người nhận
                        Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                        double origin_lat = 0;
                        double origin_lng = 0;
                        String pickUp_apartment_Number = shipment_Order.getPick_up_apartment_number();
                        String pickUp_street_Name = shipment_Order.getPick_up_street_name();
                        String pickUp_district = shipment_Order.getPick_up_district();
                        String pickUp_ward = shipment_Order.getPick_up_ward();
                        String pickUp_city = shipment_Order.getPick_up_city();
                        String pickUp_address = pickUp_apartment_Number + "," + pickUp_street_Name + "," + pickUp_district + "," + pickUp_ward + "," + pickUp_city;
                        String pickUp_encodedAddress = URLEncoder.encode(pickUp_address, StandardCharsets.UTF_8.toString());
                        String pickUp_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pickUp_encodedAddress + ";&key=" + apiKey;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(pickUp_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    origin_lat = location.getDouble("lat");
                                    origin_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                        }
                        String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                        String final_street_Name = shipment_Order.getFinal_street_name();
                        String final_district = shipment_Order.getFinal_district();
                        String final_ward = shipment_Order.getFinal_ward();
                        String final_city = shipment_Order.getFinal_city();
                        String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                        String final_encodedAddress = URLEncoder.encode(final_address, StandardCharsets.UTF_8.toString());
                        String final_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + final_encodedAddress + ";&key=" + apiKey;
                        double destination_lat = 0;
                        double destination_lng = 0;
                        try {
                            JSONObject jsonData = fetchJSONFromURL(final_urlString);
                            if (jsonData != null) {
                                JSONArray results = jsonData.getJSONArray("results");
                                if (results.length() > 0) {
                                    JSONObject firstResult = results.getJSONObject(0);
                                    JSONObject geometry = firstResult.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    destination_lat = location.getDouble("lat");
                                    destination_lng = location.getDouble("lng");
                                }
                            }
                        } catch (Exception e) {

                        }
                        //lấy dữ liệu đưa ra jsp

                        //lấy thông tin người nhận
                        Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                        int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                        Account account = generalImplement.getAccountinfor(cus_id);

                        String fname = account.getFirstName();
                        String lname = account.getLastName();
                        //sdt người gửi
                        String phone = account.getPhone_numb();
                        //tên người gửi
                        String name = fname + " " + lname;
                        Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));
                        //lấy size
                        String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                        //lấy type
                        String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                        //lấy weight
                        float package_Weight = package_Detail.getPackage_weight();

                        request.setAttribute("package_Size_value", package_Size_value);
                        request.setAttribute("package_Type_value", package_Type_value);
                        request.setAttribute("package_Weight", package_Weight);
                        request.setAttribute("name", name);
                        request.setAttribute("phone", phone);
                        request.setAttribute("shipment_Order_infor", invoice_infor);
                        request.setAttribute("final_address", final_address);
                        request.setAttribute("destination_lat", destination_lat);
                        request.setAttribute("destination_lng", destination_lng);
                        request.setAttribute("origin_lat", origin_lat);
                        request.setAttribute("origin_lng", origin_lng);
                        request.setAttribute("key_API", apiKey);
                        request.getRequestDispatcher("DRV_ReceiveOrder_deliveryOrder.jsp").forward(request, response);
                        return;
                    }
                }
            }else{
                Shipment_Order shipment_Order = driverImplement.getAddress(Integer.parseInt(order_id));
                double origin_lat;
                double origin_lng;
                String pick_up_apartment_Number = shipment_Order.getPick_up_apartment_number();
                String pick_up_street_Name = shipment_Order.getPick_up_street_name();
                String pick_up_district = shipment_Order.getPick_up_district();
                String pick_up_ward = shipment_Order.getPick_up_ward();
                String pick_up_city = shipment_Order.getPick_up_city();
                String final_apartment_Number = shipment_Order.getFinal_apartment_number();
                String final_street_Name = shipment_Order.getFinal_street_name();
                String final_district = shipment_Order.getFinal_district();
                String final_ward = shipment_Order.getFinal_ward();
                String final_city = shipment_Order.getFinal_city();
                String final_address = final_apartment_Number + "," + final_street_Name + "," + final_district + "," + final_ward + "," + final_city;
                String pick_up_address = pick_up_apartment_Number + "," + pick_up_street_Name + "," + pick_up_district + "," + pick_up_ward + "," + pick_up_city;
                String pick_up_encodedAddress = URLEncoder.encode(pick_up_address, StandardCharsets.UTF_8.toString());
                String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + pick_up_encodedAddress + ";&key=" + apiKey;
                double destination_lat = 0;
                double destination_lng = 0;
                try {
                    JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
                    if (jsonData != null) {
                        JSONArray results = jsonData.getJSONArray("results");
                        if (results.length() > 0) {
                            JSONObject firstResult = results.getJSONObject(0);
                            JSONObject geometry = firstResult.getJSONObject("geometry");
                            JSONObject location = geometry.getJSONObject("location");
                            destination_lat = location.getDouble("lat");
                            destination_lng = location.getDouble("lng");
                        }
                    }
                } catch (Exception e) {
                    Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
                }
                HttpSession session = request.getSession();
                Account account = (Account) session.getAttribute("loginSession");
                if (account != null) {
                    int driver_id = account.getAccount_id();
                    Driver driver = driverImplement.findDriverLocation(driver_id);
                    origin_lat = driver.getLatitude();
                    origin_lng = driver.getLongitude();
                    Invoice invoice_infor = driverImplement.getOrderShipmentInformation(Integer.parseInt(order_id));
                    int cus_id = invoice_infor.getShipment_order().getCustomer().getAccount().getAccount_id();
                    Account accountInfor = generalImplement.getAccountinfor(cus_id);
                    String fname = accountInfor.getFirstName();
                    String lname = accountInfor.getLastName();
                    String phone = accountInfor.getPhone_numb();
                    String name = fname + " " + lname;
                    Package_Detail package_Detail = driverImplement.getPackage_DetailInformation(Integer.parseInt(order_id));

                    //lấy size
                    String package_Size_value = package_Detail.getPackage_size().getPackage_size_value();
                    //lấy type
                    String package_Type_value = package_Detail.getPackage_type().getPackage_type_value();
                    //lấy weight
                    float package_Weight = package_Detail.getPackage_weight();
                    request.setAttribute("package_Size_value", package_Size_value);
                    request.setAttribute("package_Type_value", package_Type_value);
                    request.setAttribute("package_Weight", package_Weight);
                    request.setAttribute("name", name);
                    request.setAttribute("phone", phone);
                    request.setAttribute("shipment_Order_infor", invoice_infor);
                    request.setAttribute("final_address", final_address);
                    request.setAttribute("destination_lat", destination_lat);
                    request.setAttribute("destination_lng", destination_lng);
                    request.setAttribute("origin_lat", origin_lat);
                    request.setAttribute("origin_lng", origin_lng);
                    request.setAttribute("key_API", apiKey);
                    request.setAttribute("pick_up_address", pick_up_address);
                    request.setAttribute("check_InProvince", check_InProvince);
                    request.getRequestDispatcher("DRV_ReceiveOrder_ReceiveOrder.jsp").forward(request, response);
                    return;
                }
            }                                                  
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
