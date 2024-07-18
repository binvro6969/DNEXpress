/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Trung.GeneralImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Import_Export_Ticket;
import model.Invoice;
import model.Order_Status;


/**
 *
 * @author MINHTRUNG
 */
public class mng_ReviewTicket extends HttpServlet {   
    
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
            out.println("<title>Servlet mng_ReviewTicket</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_ReviewTicket at " + request.getContextPath() + "</h1>");
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
        String ticketId = request.getParameter("ticketId");
        Import_Export_Ticket im_ex_ticket = gerImplement.getImp_Ex_TicketById(ticketId);
        String orderId = String.valueOf(im_ex_ticket.getPackage_detail().getShipment_order().getOrder_id());
        Order_Status  order_Status = gerImplement.getOrder_StatusbyOrderID(orderId);
        Invoice invoice = gerImplement.getInvoiceByOrderId(orderId);
        String[] checkboxStatusRaw = im_ex_ticket.getCheckboxStatus();
       
        String[] checkboxStatus = new String[17];
        Arrays.fill(checkboxStatus, "notchecked");
        for (int i = 0; i < checkboxStatusRaw.length; i++) {
            checkboxStatus[Integer.parseInt(checkboxStatusRaw[i])] = "checked";
        }
        
        


        
        request.setAttribute("im_ex_ticket", im_ex_ticket);
        request.setAttribute("order_Status", order_Status);
        request.setAttribute("invoice", invoice);
        request.setAttribute("checkboxStatus", checkboxStatus);
        request.getRequestDispatcher("mng_reviewTicket.jsp").forward(request, response);
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
        
      

        String ticketStatusID = request.getParameter("ticketStatusID");
        String ticketId = request.getParameter("ticketId");
        
        if(gerImplement.updateTicketStatusId(ticketId,ticketStatusID)){
            String mgs ="Review success";                      
            request.setAttribute("mgs", mgs);
        }
        else{
            String mgs ="Review fail";
            request.setAttribute("mgs", mgs);
        }
        
        response.sendRedirect("mng_ManagePostOffice");
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
