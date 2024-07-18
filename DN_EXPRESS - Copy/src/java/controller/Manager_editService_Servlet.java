/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Impl.Duong.ManagerImplement;
import context.DBcontext;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Manager_editService_Servlet extends HttpServlet {
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
            out.println("<title>Servlet Manager_editService_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Manager_editService_Servlet at " + request.getContextPath() + "</h1>");
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
         int serviceTypeId = Integer.parseInt(request.getParameter("service_type_id"));

        // Lấy thông tin Service Type và Service Property từ database
        Service_Type serviceType = managerImplement.getServiceTypeById(serviceTypeId);
        List<Service_Property> serviceProperties = managerImplement.getService_PropertyByID(serviceTypeId);
        
         request.setAttribute("detailSer", serviceType);
        
         request.setAttribute("serProperList", serviceProperties);
         
        
        request.getRequestDispatcher("MNG_Edit_Service_Details.jsp").forward(request, response);
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
         String service_TypeID = request.getParameter("serID");
    String service_TypeName = request.getParameter("serName");
    
    int serTypeID = Integer.parseInt(service_TypeID);
    
    String[] proIds = request.getParameterValues("proId");
    String[] proNames = request.getParameterValues("proName");
    String[] proPrices = request.getParameterValues("proPrice");
    
  
    
     
    for (int i = 0; i < proIds.length; i++) {
        int propertyID = Integer.parseInt(proIds[i]);
        String propertyName = proNames[i];
        float propertyPrice = Float.parseFloat(proPrices[i]);
        
     
        Service_Type serviceType = new Service_Type(serTypeID, service_TypeName); 
        Service_Property updatedProperty = new Service_Property(serviceType, propertyID, propertyName, propertyPrice);
        

        managerImplement.updateServiceType(serviceType, updatedProperty);
    }

    response.sendRedirect("Manager_editService_Servlet?service_type_id=" + serTypeID );
        
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
