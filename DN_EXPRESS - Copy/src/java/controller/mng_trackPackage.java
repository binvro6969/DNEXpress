/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Trung.DriverImplement;
import Impl.Trung.GeneralImplement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Invoice;
import model.Order_Status;
import model.Package_Detail;

/**
 *
 * @author MINHTRUNG
 */
public class mng_trackPackage extends HttpServlet {
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
            out.println("<title>Servlet mng_trackPackage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_trackPackage at " + request.getContextPath() + "</h1>");
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
       String mgs = "Enter information to track package";
       request.setAttribute("msg", mgs);
       request.getRequestDispatcher("mng_trackingPackage.jsp").forward(request, response);
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
        String searchIndex = request.getParameter("searchIndex");
        Package_Detail packageDetail = gerImplement.getPackageDetailsByOrderId(searchIndex);
        Order_Status  order_Status = gerImplement.getOrder_StatusbyOrderID(searchIndex);
        Invoice invoice = gerImplement.getInvoiceByOrderId(searchIndex);
        
        String mgs = "No package can be found";
        if (order_Status != null && order_Status.getShipment_order() != null && order_Status.getShipment_Order_driver() != null) {
            Float latitude = order_Status.getShipment_Order_driver().getDriver().getLatitude();
            Float longitude = order_Status.getShipment_Order_driver().getDriver().getLongitude();
            request.setAttribute("latitude", latitude);
            request.setAttribute("longitude", longitude);
        } else {
            // Handle the case where one of these objects is null
        }

        
        request.setAttribute("msg", mgs);
        request.setAttribute("packageDetail", packageDetail);
        request.setAttribute("order_Status", order_Status);
        request.setAttribute("invoice", invoice);
        
        
        request.getRequestDispatcher("mng_trackingPackage.jsp").forward(request, response);
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
