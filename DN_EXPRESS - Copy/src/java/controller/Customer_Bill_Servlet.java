/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.An.DriverImplement;
import Impl.Minh.CustomerImplement;
import Impl.Minh.OrderImpl;
import Impl.Minh.PackageImpl;
import Impl.Minh.ServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Customer;
import model.Delivery_Type;
import model.Driver;
import model.Invoice;
import model.Package_Detail;
import model.Package_Size;
import model.Package_Type;
import model.Payment_Method;
import model.Service_Property;
import model.Shipment_Order;

/**
 *
 * @author dangq
 */
public class Customer_Bill_Servlet extends HttpServlet {

    static CustomerImplement customerImpl = new CustomerImplement();
    static OrderImpl orderImpl = new OrderImpl();
    static ServiceImpl serviceImpl = new ServiceImpl();
    static PackageImpl packageImpl = new PackageImpl();
    static DriverImplement driverImpl = new DriverImplement();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Customer_Bill_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer_Bill_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("UTF-8");
        // Get the session
        HttpSession session = request.getSession();

        Account account = (Account) session.getAttribute("loginSession");

        if (session.getAttribute("loginSession") == null) {
            response.sendRedirect("Authentication_LoginMain_Servlet");
        } else {

            // Retrieve session attributes
            String sendName = (String) session.getAttribute("sendName");
            String sendPhone = (String) session.getAttribute("sendPhone");
            String recName = (String) session.getAttribute("recName");
            String recPhone = (String) session.getAttribute("recPhone");
            String sendAdd = (String) session.getAttribute("sendAdd");
//            String recAdd = (String) session.getAttribute("recAdd");
            Integer typeShipmentId = (Integer) session.getAttribute("typeShipmentId");
            String requestDeliveryDate = (String) session.getAttribute("requestDeliveryDate");
            Integer sizeId = (Integer) session.getAttribute("sizeId");
            Float weight = (Float) session.getAttribute("weight");
            Integer typePackageId = (Integer) session.getAttribute("typePackageId");
            Integer warrantyValue = (Integer) session.getAttribute("warrantyValue");
            Float trueValue = (Float) session.getAttribute("trueValue");
            String note = (String) session.getAttribute("note");
            Integer paymentId = (Integer) session.getAttribute("paymentId");
            Boolean inProvince = (Boolean) session.getAttribute("inProvince");
            String receivingNote = (String) session.getAttribute("receivingNote");
            String requestReceiveDate = (String) session.getAttribute("requestReceiveDate");
            Float totalMoney = (Float) session.getAttribute("totalMoney");
            Float serviceFee = (Float) session.getAttribute("serviceFee");
            String createdDate = (String) session.getAttribute("createdDate");

            String filePath = (String) session.getAttribute("filePath");

            // Retrieve sender address components from session
            String senderStreetNumber = (String) session.getAttribute("senderStreetNumber");
            String senderStreetName = (String) session.getAttribute("senderStreetName");
            String senderWard = (String) session.getAttribute("senderWard");
            String senderDistrict = (String) session.getAttribute("senderDistrict");
            String senderCity = (String) session.getAttribute("senderCity");
            String senderCountry = (String) session.getAttribute("senderCountry");

            // Retrieve receiver address components from session
            String receiverStreetNumber = (String) session.getAttribute("receiverStreetNumber");
            String receiverStreetName = (String) session.getAttribute("receiverStreetName");
            String receiverWard = (String) session.getAttribute("receiverWard");
            String receiverDistrict = (String) session.getAttribute("receiverDistrict");
            String receiverCity = (String) session.getAttribute("receiverCity");
            String receiverCountry = (String) session.getAttribute("receiverCountry");

//        requestDeliveryDate = requestDeliveryDate.replace(" ", "T");
//        requestReceiveDate = requestReceiveDate.replace(" ", "T");
//        createdDate = createdDate.replace(" ", "T");
            //Insert shipment order 
            Shipment_Order shipmentOrder = new Shipment_Order();

            Customer customer = new Customer();
            customer.setAccount(account);

            shipmentOrder.setCustomer(customer);

            customer.getAccount().setFirstName(customerImpl.getFirstAndLastName(sendName)[0]);
            customer.getAccount().setLastName(customerImpl.getFirstAndLastName(sendName)[1]);
            customer.getAccount().setPhone_numb(sendPhone);

            shipmentOrder.setFinal_receiver_name(recName);
            shipmentOrder.setFinal_receiver_phone(recPhone);

            //Order addresses
//            String[] pickAddress = splitAddress(sendAdd);
//            String[] finAddress = splitAddress(recAdd);
            shipmentOrder.setCreated_date(createdDate);

            shipmentOrder.setPick_up_apartment_number(senderStreetNumber);
            shipmentOrder.setPick_up_street_name(senderStreetName);
            shipmentOrder.setPick_up_district(senderDistrict);
            shipmentOrder.setPick_up_ward(senderWard);
            shipmentOrder.setPick_up_city(senderCity);

            shipmentOrder.setFinal_apartment_number(receiverStreetNumber);
            shipmentOrder.setFinal_street_name(receiverStreetName);
            shipmentOrder.setFinal_district(receiverDistrict);
            shipmentOrder.setFinal_ward(receiverWard);
            shipmentOrder.setFinal_city(receiverCity);
            shipmentOrder.setIn_province(inProvince);

//        //Insert package detail
            Package_Detail packageDetail = new Package_Detail();

            packageDetail.setShipment_order(shipmentOrder);

            List<Delivery_Type> deliveryTypeList = (List<Delivery_Type>) session.getAttribute("deliveryTypes");
            List<Package_Type> packageTypeList = (List<Package_Type>) session.getAttribute("packageTypes");
            List<Package_Size> packageSizeList = (List<Package_Size>) session.getAttribute("packageSizes");

            for (Delivery_Type dt : deliveryTypeList) {
                if (dt.getDelivery_type_id() == typeShipmentId) {
                    packageDetail.setDelivery_type(dt);
                }
            }
            for (Package_Type pt : packageTypeList) {
                if (pt.getPackage_type_id() == typePackageId) {
                    packageDetail.setPackage_type(pt);
                }
            }
            for (Package_Size ps : packageSizeList) {
                if (ps.getPackage_size_id() == sizeId) {
                    packageDetail.setPackage_size(ps);
                }
            }

            packageDetail.setRequested_delivery_date(requestDeliveryDate);
            packageDetail.setPackage_weight(weight);
            packageDetail.setPackage_note(note);
            packageDetail.setPackage_receiving_note(receivingNote);
            packageDetail.setPackage_value(trueValue);
            if (typeShipmentId == 4) {
                packageDetail.setRequested_receiving_date(requestReceiveDate);
            }

            packageDetail.setPackage_img(filePath);

            //Insert Package Service
            Service_Property serviceProperty = new Service_Property();
            serviceProperty.setService_property_id(warrantyValue);

            //Insert invoice
            Invoice invoice = new Invoice();
            invoice.setShipment_order(shipmentOrder);
            invoice.setService_fee(serviceFee);
            invoice.setTotal_amount(totalMoney);

            List<Payment_Method> paymentList = (List<Payment_Method>) session.getAttribute("paymentOptions");

            for (Payment_Method pm : paymentList) {
                if (pm.getPayment_method_id() == paymentId) {
                    invoice.setPayment_method(pm);
                }
            }

            Boolean isExecuted = (Boolean) session.getAttribute("isExecuted");

            if (isExecuted == null || !isExecuted) {
                orderImpl.addShipmentOrder(shipmentOrder);
                packageImpl.addPackageDetails(packageDetail);
                serviceImpl.addPackageService(serviceProperty, packageDetail);
                orderImpl.addInvoice(invoice);

                session.setAttribute("isExecuted", true);
            }
            int orderId = shipmentOrder.getOrder_id();
            session.setAttribute("orderId", orderId);

//            String origin = "80,Lê Văn Hiến,Khuê Mỹ,Ngũ Hành Sơn,Đà Nẵng,Việt Nam";
//            String city = "Đà Nẵng";

            System.out.println("city: " + senderCity);
            Driver driver = driverImpl.chooseDriver(sendAdd, senderCity);
            driverImpl.insertShipmentOrderDriver(shipmentOrder, driver);

            request.getRequestDispatcher("CUS_CreateOrder_Bill.jsp").forward(request, response);

            // Remove all session attributes
            session.removeAttribute("sendName");
            session.removeAttribute("sendPhone");
            session.removeAttribute("recName");
            session.removeAttribute("recPhone");
            session.removeAttribute("sendAdd");
            session.removeAttribute("recAdd");
            session.removeAttribute("typeShipmentId");
            session.removeAttribute("requestDeliveryDate");
            session.removeAttribute("sizeId");
            session.removeAttribute("weight");
            session.removeAttribute("typePackageId");
            session.removeAttribute("warrantyValue");
            session.removeAttribute("trueValue");
            session.removeAttribute("note");
            session.removeAttribute("paymentId");
            session.removeAttribute("inProvince");
            session.removeAttribute("receivingNote");
            session.removeAttribute("requestReceiveDate");
            session.removeAttribute("totalMoney");
            session.removeAttribute("serviceFee");
            session.removeAttribute("createdDate");
            session.removeAttribute("filePath");
        }

    }

    public String[] splitAddress(String address) {
        String apartmentNumber = "0"; // Default value for apartment number
        String restOfAddress = address;

        // Check if the first part of the address is a number
        String[] parts = address.split("\\s+", 2);
        if (parts.length > 1 && parts[0].matches("\\d+")) {
            apartmentNumber = parts[0];
            restOfAddress = parts[1];
        }

        String[] addressParts = restOfAddress.split(",");

        String[] result = new String[]{
            apartmentNumber,
            addressParts.length > 0 ? addressParts[0].trim() : "",
            addressParts.length > 1 ? addressParts[1].trim() : "",
            addressParts.length > 2 ? addressParts[2].trim() : "",
            addressParts.length > 3 ? addressParts[3].trim() : "",
            addressParts.length > 4 ? addressParts[4].trim() : "",
            addressParts.length > 5 ? addressParts[5].trim() : ""
        };

//        System.out.println("Before switch: result[4] = " + result[4]);
        // Ensure to trim any extra whitespace before comparing
        result[4] = result[4].trim();

        switch (result[4]) {
            case "Da Nang":
                result[4] = "Đà Nẵng";
                break;
            case "Ha Noi":
            case "Hanoi":
                result[4] = "Hà Nội";
                break;
            case "Ho Chi Minh":
                result[4] = "Hồ Chí Minh";
                break;
            case "Hai Phong":
                result[4] = "Hải Phòng";
                break;
            default:
                break;
        }

        // Add debug statements to see the value after the switch statement
//        System.out.println("After switch: result[4] = " + result[4]);
        return result;

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
