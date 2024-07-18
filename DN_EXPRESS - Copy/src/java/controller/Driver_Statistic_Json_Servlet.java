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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import org.json.JSONObject;
import model.Monthly_Statistic;
import org.json.JSONArray;

/**
 *
 * @author dangq
 */
public class Driver_Statistic_Json_Servlet extends HttpServlet {

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
            out.println("<title>Servlet DriverStatistic</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DriverStatistic at " + request.getContextPath() + "</h1>");
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
        if (account == null) {
            response.sendRedirect("Authentication_LoginMain_Servlet");
        } else {
            int driverId = account.getAccount_id(); //Must change later

            //Total Order
            int totalOrders = driverImplement.getTotalOrders(driverId);
            int finishedOrders = driverImplement.getDeliveredOrders(driverId);

            int strokeDasharray = 200;
            double orderPercentage = (double) finishedOrders / totalOrders * 100;
            double orderStrokeDashoffset = strokeDasharray * (1 - orderPercentage / 100);

            //Total Rating
            double rating = driverImplement.getDriverRating(driverId);
            double ratingPercentage = (rating / 10) * 100;
            double ratingStrokeDashoffset = strokeDasharray * (1 - ratingPercentage / 100);

            //Get year
            int year = Integer.parseInt(request.getParameter("year"));
//        int year = 2023;
            List<Monthly_Statistic> monthlyStats = driverImplement.getMonthlyStatistics(driverId, year);

            JSONArray jsonArray = new JSONArray();
            for (Monthly_Statistic stats : monthlyStats) {
                JSONObject json = new JSONObject();
                json.put("month", stats.getMonth());
                json.put("orderCount", stats.getOrderCount());
                json.put("avgRating", stats.getAvgRating());
                json.put("totalAmount", stats.getTotalAmount());
                jsonArray.put(json);
            }

            // Create JSON response
            JSONObject json = new JSONObject();
            json.put("orderPercentage", orderPercentage);
            json.put("orderStrokeDashoffset", orderStrokeDashoffset);
            json.put("strokeDasharray", strokeDasharray);

            json.put("ratingPercentage", ratingPercentage);
            json.put("ratingStrokeDashoffset", ratingStrokeDashoffset);

            // Set response
            JSONObject responseData = new JSONObject();
            responseData.put("overallStats", json);
            responseData.put("monthlyStats", jsonArray);

            response.setContentType("application/json");
            response.getWriter().write(responseData.toString());
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
