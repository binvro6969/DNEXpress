/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Impl.Duong.ManagerImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service_Property;
import model.Service_Type;

/**
 *
 * @author DuongPhan
 */
public class Manager_addService_Servlet extends HttpServlet {

    static ManagerImplement managerImplement = new ManagerImplement();

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
            out.println("<title>Servlet Manager_addService_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Manager_addService_Servlet at " + request.getContextPath() + "</h1>");
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

        String serviceTypeName = request.getParameter("serName");
        String[] propertyNames = request.getParameterValues("proName");
        String[] propertyPrices = request.getParameterValues("proPrice");

        List<Service_Property> serviceProperties = new ArrayList<>();
        boolean hasError = false;
        String errorMessage = "";

        if (managerImplement.isServiceNameExists(serviceTypeName)) {
            errorMessage = "Service name already exists. Please enter a different name.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("MNG_Add_Service_Details.jsp").forward(request, response);
            return;
        }

        for (int i = 0; i < propertyNames.length; i++) {
            String propertyName = propertyNames[i];
            try {
                float propertyPrice = Float.parseFloat(propertyPrices[i]);
                Service_Property serviceProperty = new Service_Property();
                serviceProperty.setService_property_value(propertyName);
                serviceProperty.setService_property_price(propertyPrice);
                serviceProperties.add(serviceProperty);
            } catch (NumberFormatException e) {
                hasError = true;
                errorMessage = "Invalid price for property: " + propertyName;
                break;
            }
        }

        if (hasError) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("MNG_Add_Service_Details.jsp").forward(request, response);
        } else {
            Service_Type serviceType = new Service_Type();
            serviceType.setService_type_value(serviceTypeName);

            managerImplement.addServiceTypeWithProperties(serviceType, serviceProperties);

            response.sendRedirect("Manager_listService_Servlet");
        }
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
