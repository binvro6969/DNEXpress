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
import javax.servlet.http.HttpSession;
import model.Account;
import model.Invoice;
import model.Order_Status;
import model.Package_Detail;

/**
 *
 * @author MINHTRUNG
 */
public class sta_inspectPackage_createTicket extends HttpServlet {
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
            out.println("<title>Servlet sta_inspectPackage_createTicket</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sta_inspectPackage_createTicket at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("sta_inspectPackage_createTicket.jsp").forward(request, response);
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
        String orderId = request.getParameter("searchIndex");

      
        
        HttpSession session = request.getSession(); 

        // Ensure the session attribute is not null
        Account staff = null;
        if (session.getAttribute("loginSession") != null) {
            staff = (Account) session.getAttribute("loginSession");
        }

        String staff_id = "";
        if (staff != null) {
            staff_id = String.valueOf(staff.getAccount_id());
            
        } else {
            System.out.println("session is null.");
            return;
        }
        
//        String post_office_id = gerImplement.get;
        
        
        if( gerImplement.isTicketValid(orderId)){
        Package_Detail packageDetail = gerImplement.getPackageDetailsByOrderId(orderId);
        Order_Status  order_Status = gerImplement.getOrder_StatusbyOrderID(orderId);
        Invoice invoice = gerImplement.getInvoiceByOrderId(orderId);
              
        
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
        
        
        request.getRequestDispatcher("sta_inspectPackage_createTicket.jsp").forward(request, response);
        }
        else{
            String mgs = "No valid order found";
            request.setAttribute("mgs", mgs);
            System.out.println(mgs);
                    
            request.getRequestDispatcher("sta_inspectPackage_createTicket.jsp").forward(request, response);
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
