/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Minh.DriverImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author dangq
 */
public class Driver_Statistic_Jsp_Servlet extends HttpServlet {

    static DriverImplement driverImplement = new DriverImplement();

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
            out.println("<title>Servlet Driver_Statistic_Jsp_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Driver_Statistic_Jsp_Servlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int driverId = account.getAccount_id();
        if(session.getAttribute("loginSession")==null){
            response.sendRedirect("Authentication_LoginMain_Servlet");
        } else {
            List<Integer> orderYears = driverImplement.getDistinctOrderYears(driverId);
            int totalOrders = driverImplement.getTotalOrders(driverId);
            int finishedOrders = driverImplement.getDeliveredOrders(driverId);
            double rating = driverImplement.getDriverRating(driverId);
            double totalAmount = driverImplement.getDriverIncome(driverId);
        
        // Set attributes
            request.setAttribute("orderYears", orderYears);
            request.setAttribute("totalOrders", totalOrders);
            request.setAttribute("finishedOrders", finishedOrders);
            request.setAttribute("rating", rating);
            request.setAttribute("totalAmount", totalAmount);


        // Forward to the JSP
            request.getRequestDispatcher("DRV_Statistic.jsp").forward(request, response);
        }
        

        

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
