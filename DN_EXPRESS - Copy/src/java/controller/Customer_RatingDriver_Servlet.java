/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Dinh.CustomerImplement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Shipment_Order;

/**
 *
 * @author dangq
 */
public class Customer_RatingDriver_Servlet extends HttpServlet {

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
            out.println("<title>Servlet Customer_RatingDriver_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Customer_RatingDriver_Servlet at " + request.getContextPath() + "</h1>");
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
        request.getSession().removeAttribute("msg");

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
        String rate_raw = request.getParameter("rate");
        String feedback = request.getParameter("feedback");
        String orderId_raw = request.getParameter("orderID");

        if (rate_raw == null || rate_raw.isEmpty()) {
            request.getSession().setAttribute("msg", "Rating is required");
            response.sendRedirect("CUS_RatingDriver.jsp?orderID=" + orderId_raw);
            return;
        }

        int rate;
        try {
            rate = Integer.parseInt(rate_raw);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("msg", "Invalid rating value.");
            response.sendRedirect("CUS_RatingDriver.jsp?orderID=" + orderId_raw);
            return;
        }

        int orderId;
        try {
            orderId = Integer.parseInt(orderId_raw);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("msg", "Invalid order ID.");
            response.sendRedirect("CUS_RatingDriver.jsp?orderID=" + orderId_raw);
            return;
        }

        request.getSession().removeAttribute("msg");

        Shipment_Order smo = new Shipment_Order();
        smo.setCust_driver_rating(rate);
        smo.setCust_feedback(feedback);

        CustomerImplement cusIpl = new CustomerImplement();
        cusIpl.ratingDriver(smo, orderId);
        response.sendRedirect("Customer_OrderHistory_Servlet");
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
