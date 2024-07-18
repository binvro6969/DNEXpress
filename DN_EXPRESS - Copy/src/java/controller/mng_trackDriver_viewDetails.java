/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Trung.DriverImplement;
import Impl.Trung.GeneralImplement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Driver;
import model.Package_Detail;
import model.Package_Size;
import model.Package_Type;
import model.Shipment_Order;
import model.Vehicle;

/**
 *
 * @author MINHTRUNG
 */
public class mng_trackDriver_viewDetails extends HttpServlet {
    static DriverImplement driverImplement = new DriverImplement();
    static GeneralImplement gerImplement = new GeneralImplement();

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mng_trackDriver_viewDetails</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_trackDriver_viewDetails at " + request.getContextPath() + "</h1>");
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
        
        ArrayList<Driver> driverList = new ArrayList<>();
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        
        ArrayList<Package_Detail> packageDetailsList = new ArrayList<>();
        ArrayList<Package_Size> packageSizesList = new ArrayList<>();
        ArrayList<Package_Type> packageTypesList = new ArrayList<>();
        String driver_id = request.getParameter("driver_id");
        
        Float latitude = Float.parseFloat(request.getParameter("latitude"));
        Float longitude = Float.parseFloat(request.getParameter("longitude"));
        driverList = driverImplement.getDriver(driver_id, null, null); 
        vehicleList = driverImplement.getVehicleByDriverID(driver_id);
        packageDetailsList = gerImplement.getPackageDetailsByDriverId(driver_id);
       
        boolean haveShipment = !packageDetailsList.isEmpty();
        

                
        
        if (!packageDetailsList.isEmpty()) {
            packageTypesList = gerImplement.getPackageTypes();
            packageSizesList = gerImplement.getPackageSizes();
            request.setAttribute("packageDetailsList", packageDetailsList);
            request.setAttribute("packageTypesList", packageTypesList);
            request.setAttribute("packageSizesList", packageSizesList);
        } 

       request.setAttribute("haveShipment", haveShipment);
       request.setAttribute("latitude", latitude);
       request.setAttribute("longitude", longitude);
       request.setAttribute("driverList", driverList);
       request.setAttribute("vehicleList", vehicleList);

       request.getRequestDispatcher("mng_trackDriver_viewDetails.jsp").forward(request, response);
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
        
    
    BufferedReader reader = request.getReader();
    StringBuilder requestBody = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        requestBody.append(line);
    }
    
    

    // Parse JSON using Gson
    JsonObject jsonObject = new Gson().fromJson(requestBody.toString(), JsonObject.class);

    // Retrieve orderId from JsonObject
    String orderId = jsonObject.get("orderId").getAsString();
    System.out.println("Received orderId: " + orderId);
    
    
    // Store orderId in session
    HttpSession session = request.getSession();
    session.setAttribute("selectedOrderId", orderId);
    
    Package_Detail packageDetail = gerImplement.getPackageDetailsByOrderId(orderId);
    
    StringBuilder htmlBuilder = new StringBuilder();
    
    htmlBuilder.append("<header class=\"header\">");
    htmlBuilder.append("<h1>Shipment Information</h1>");
    htmlBuilder.append("</header>");
    htmlBuilder.append("<form action=\"#\" class=\"form\" method=\"POST\">");
    htmlBuilder.append("<div>");
    htmlBuilder.append("<h2>Sender Information</h2>");
    htmlBuilder.append("<div class=\"card\">");
    htmlBuilder.append("<address>");
    htmlBuilder.append("<strong>Name</strong>   :").append(" ").append(packageDetail.getShipment_order().getCustomer().getAccount().getFirstName()).append(" ").append(packageDetail.getShipment_order().getCustomer().getAccount().getLastName()).append("<br />");
    htmlBuilder.append("<strong>Phone</strong>   :").append(" ").append(packageDetail.getShipment_order().getCustomer().getAccount().getPhone_numb()).append("<br />");
    htmlBuilder.append("<strong>Address</strong>   :").append(" ").append(packageDetail.getShipment_order().getPick_up_apartment_number()).append(", ").append(packageDetail.getShipment_order().getPick_up_street_name()).append(", ").append(packageDetail.getShipment_order().getPick_up_district()).append(", ");
    htmlBuilder.append(packageDetail.getShipment_order().getPick_up_ward()).append(", ").append(packageDetail.getShipment_order().getPick_up_city());
    htmlBuilder.append("</address>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("<div>");
    htmlBuilder.append("<h2>Receiver Information</h2>");
    htmlBuilder.append("<div class=\"card\">");
    htmlBuilder.append("<address>");
    htmlBuilder.append("<strong>Name</strong>   :").append(" ").append(packageDetail.getShipment_order().getFinal_receiver_name()).append("<br />");
    htmlBuilder.append("<strong>Phone</strong>   :").append(" ").append(packageDetail.getShipment_order().getFinal_receiver_phone()).append("<br />");
    htmlBuilder.append("<strong>Address</strong>   :").append(" ").append(packageDetail.getShipment_order().getFinal_apartment_number()).append(", ").append(packageDetail.getShipment_order().getFinal_street_name()).append(", ").append(packageDetail.getShipment_order().getFinal_district()).append(", ");
    htmlBuilder.append(packageDetail.getShipment_order().getFinal_ward()).append(", ").append(packageDetail.getShipment_order().getFinal_city());
    htmlBuilder.append("</address>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("<div>");
    htmlBuilder.append("<h2>Package Details</h2>");
    htmlBuilder.append("<div class=\"card_information\">");
    htmlBuilder.append("<table>");
    htmlBuilder.append("<tbody>");
    htmlBuilder.append("<tr>");
    htmlBuilder.append("<td>Package Type</td>");
    htmlBuilder.append("<td>");
    htmlBuilder.append(packageDetail.getPackage_type().getPackage_type_value());
    htmlBuilder.append("</td>");
    htmlBuilder.append("</tr>");
    htmlBuilder.append("<tr>");
    htmlBuilder.append("<td>Package Size</td>");
    htmlBuilder.append("<td>");   
    htmlBuilder.append(packageDetail.getPackage_size().getPackage_size_value());
    htmlBuilder.append("</td>");
    htmlBuilder.append("</tr>");
    htmlBuilder.append("<tr>");
    htmlBuilder.append("<td>Package Weight</td>");
    htmlBuilder.append("<td>").append(packageDetail.getPackage_weight()).append("</td>");
    htmlBuilder.append("</tr>");
    htmlBuilder.append("<tr>");
    htmlBuilder.append("<td>Value of Package</td>");
    htmlBuilder.append("<td>").append(packageDetail.getPackage_value()).append("</td>");
    htmlBuilder.append("</tr>");
    htmlBuilder.append("</tbody>");
    htmlBuilder.append("</table>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</form>");
    
    Map<String, Object> responseData = new HashMap<>();
    responseData.put("htmlContent", htmlBuilder.toString());
    
    Gson gson = new Gson();
    String json = gson.toJson(responseData);


    // Send success response
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
   
    PrintWriter out = response.getWriter();
    out.print(json);

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
