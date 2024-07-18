/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Trung.DriverImplement;
import Impl.Trung.GeneralImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Import_Export_Ticket;
import model.Manager;
import model.Package_Detail;
import model.Post_Office;
import model.Staff;
import model.Ticket_Status;

/**
 *
 * @author MINHTRUNG
 */
public class mng_inspectPackage_createTicket_2 extends HttpServlet {
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
            out.println("<title>Servlet mng_inspectPackage_createTicket_2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_inspectPackage_createTicket_2 at " + request.getContextPath() + "</h1>");
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
        
    String[] checkboxValues = request.getParameterValues("checkbox");
    String orderId = request.getParameter("orderId");
    String integrity = request.getParameter("integrity");
    String labeling = request.getParameter("labeling");
    String quantity = request.getParameter("quantity");
    String documentation = request.getParameter("documentation");
    String ticketNote = request.getParameter("ticketNote");
    String ticketType = request.getParameter("selected");

    System.out.println("Order ID: " + orderId);
    System.out.println("Checkbox Values: " + Arrays.toString(checkboxValues));
    System.out.println("Ticket type: " +ticketType);
    
    HttpSession session = request.getSession(); 

        // Ensure the session attribute is not null
        Account staffraw = null;
        if (session.getAttribute("loginSession") != null) {
            staffraw = (Account) session.getAttribute("loginSession");
        }

        String staff_id = "";
        if (staffraw != null) {
            staff_id = String.valueOf(staffraw.getAccount_id());
            
        } else {
            System.out.println("session is null.");
            return;
        }
        
    String post_office_id = String.valueOf(gerImplement.getStaffById(staff_id).getPost_office().getPost_office_id());

    Ticket_Status ts = gerImplement.getTicketStatusByStatus("Processing");
    Staff staff = gerImplement.getStaffById(staff_id);
    Post_Office po = gerImplement.getPostOfficeByID(Integer.parseInt(post_office_id));
    Manager manager = gerImplement.getRandomManagerIdByPostOfficeId(post_office_id);
    Package_Detail packageDetail = gerImplement.getPackageDetailsByOrderId(orderId);
    Date currentDate = new Date();
    
    Import_Export_Ticket im_ex_ticket = new Import_Export_Ticket();
    
    im_ex_ticket.setPost_office(po);
    im_ex_ticket.setStaff(staff);
    im_ex_ticket.setManager(manager);
    im_ex_ticket.setPackage_detail(packageDetail);
    im_ex_ticket.setTicket_status(ts);
    im_ex_ticket.setCreate_date(currentDate);
    im_ex_ticket.setNote(ticketNote);
    im_ex_ticket.setIntegrity(integrity);
    im_ex_ticket.setLabeling(labeling);
    im_ex_ticket.setQuantity(quantity);
    im_ex_ticket.setDocumentation(documentation);
    im_ex_ticket.setCheckboxStatus(checkboxValues);
    im_ex_ticket.setTicketType(ticketType);
    System.out.println(im_ex_ticket);
    
    String mgs;
    if(gerImplement.addImp_Ex_Ticket(im_ex_ticket)){
        mgs="Success";
    } else {
        mgs="Fail to add ticket";
    }
    
    request.setAttribute("mgs", mgs);
    response.sendRedirect("sta_inspectPackage");
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
