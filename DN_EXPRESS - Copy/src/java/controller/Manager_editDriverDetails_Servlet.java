/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Impl.Duong.DriverImplement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Driver;

/**
 *
 * @author DuongPhan
 */
public class Manager_editDriverDetails_Servlet extends HttpServlet {
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
        String accountIdParam = request.getParameter("account_id");
             String driverTypeIdParam = request.getParameter("driver_type_id");

             if (accountIdParam == null || accountIdParam.isEmpty() || driverTypeIdParam == null || driverTypeIdParam.isEmpty()) {
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
                 return;
             }

             int drID = Integer.parseInt(accountIdParam);
             int driver_typeID = Integer.parseInt(driverTypeIdParam);

             Driver d = driverImplement.getDriverByID(drID);

             if (d == null) {
                 response.sendError(HttpServletResponse.SC_NOT_FOUND, "Driver not found");
                 return;
             }

             System.out.println("Driver: " + d);
             System.out.println("Driver Type: " + d.getDriver_type());


             request.setAttribute("detail", d);
             request.getRequestDispatcher("MNG_Edit_Driver_Details.jsp").forward(request, response);
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
