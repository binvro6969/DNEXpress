/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import Impl.Trung.DriverImplement;
import Impl.Trung.GeneralImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Import_Export_Ticket;
import model.Manager;

/**
 *
 * @author MINHTRUNG
 */
public class mng_ManagePostOffice extends HttpServlet {
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
            out.println("<title>Servlet mng_ManagePostOffice</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_ManagePostOffice at " + request.getContextPath() + "</h1>");
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

        
        Account manager = null;
        if (session.getAttribute("loginSession") != null) {
            manager = (Account) session.getAttribute("loginSession");
        }

        String mng_id = "";
        if (manager != null) {
            mng_id = String.valueOf(manager.getAccount_id());
            System.out.println("mng_id: " + mng_id);
        } else {
            System.out.println("Manager session is null.");
            return;
        }

        ArrayList<Import_Export_Ticket> ticketList = gerImplement.getImp_Ex_TicketByManagerId(mng_id);
        
        String post_office_id = String.valueOf(gerImplement.getManagerById(mng_id).getPost_office().getPost_office_id());
         System.out.println("post_office_id" + post_office_id);
        int numbOfWaitList = gerImplement.countTicketByStatusId("1",post_office_id);
        int numbOfImportTicket = gerImplement.countTicketByType("Import",post_office_id);
        int numbOfExportTicket = gerImplement.countTicketByType("Export",post_office_id);
        int numOfStore = gerImplement.calculateImportExportTicketDifference(post_office_id);
       
        request.setAttribute("numbOfWaitList", numbOfWaitList);
        request.setAttribute("numbOfImportTicket", numbOfImportTicket);
        request.setAttribute("numbOfExportTicket", numbOfExportTicket);
        request.setAttribute("numOfStore", numOfStore);
        request.setAttribute("ticketList", ticketList);

        // Forward to the JSP page
        request.getRequestDispatcher("mng_managePostOffice.jsp").forward(request, response);
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
