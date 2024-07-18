/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Impl.Duong.DriverImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Driver;
import model.Driver_type;

/**
 *
 * @author DuongPhan
 */
public class Manager_ListDriverStaff_Servlet extends HttpServlet {

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
            out.println("<title>Servlet Manager_ListDriverStaff_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Manager_ListDriverStaff_Servlet at " + request.getContextPath() + "</h1>");
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
        String dtIdStr = request.getParameter("dtId");
        int sId1 = 0;

        if (dtIdStr != null && !dtIdStr.trim().isEmpty()) {
            try {
                sId1 = Integer.parseInt(dtIdStr);
            } catch (NumberFormatException e) {

                e.printStackTrace();
            }
        } else {
            // Handle the case where dtId is missing or empty, e.g., set a default value or return an error response
            // For example, you can set a default value or redirect to an error page
        }

        List<Driver_type> drType = driverImplement.getAllDriverTypes();
        List<Driver> dList = driverImplement.listDriverType(sId1);

        System.out.println("drType: " + drType);
        System.out.println("drList: " + dList);

        request.setAttribute("drType", drType);
        request.setAttribute("dList", dList);

        System.out.println("test: " + dList);
        request.getRequestDispatcher("MNG_Manage_Driver.jsp").forward(request, response);
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
