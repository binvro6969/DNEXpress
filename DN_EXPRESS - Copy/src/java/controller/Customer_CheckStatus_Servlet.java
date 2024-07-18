/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Minh.OrderImpl;
import Impl.Minh.PackageImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order_Status;
import model.Package_Detail;
import model.Shipment_Order;

/**
 *
 * @author dangq
 */
public class Customer_CheckStatus_Servlet extends HttpServlet {

    static OrderImpl orderImpl = new OrderImpl();
    static PackageImpl packageImpl = new PackageImpl();
    
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
            out.println("<title>Servlet Customer_CheckStatus_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer_CheckStatus_Servlet at " + request.getContextPath() + "</h1>");
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

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        List<Order_Status> orderStatusList = orderImpl.getOrderStatusbyOrderId(orderId);

        Shipment_Order shipmentOrder = new Shipment_Order();
        shipmentOrder = orderImpl.getShipmentOrdersByOrderId(orderId);
        System.out.println("order: " + shipmentOrder);
        
        Package_Detail packageDetail = new Package_Detail();
        packageDetail = packageImpl.getPackageDetailsByOrderId(orderId);

//        for (Order_Status status : orderStatusList) {
//            System.out.println("status: " + status);
//
//        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime created_datetime = shipmentOrder.getCreated_date();
        System.out.println(created_datetime);
        String createdDate = created_datetime.toLocalDate().format(dateFormatter);
        String createdTime = created_datetime.toLocalTime().format(timeFormatter);

        request.setAttribute("order", shipmentOrder);
        request.setAttribute("pack", packageDetail);
        request.setAttribute("statusList", orderStatusList);
        request.setAttribute("createdDate", createdDate);
        request.setAttribute("createdTime", createdTime);

        request.getRequestDispatcher("CUS_CheckOrder_Status.jsp").forward(request, response);
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
