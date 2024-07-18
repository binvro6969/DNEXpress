/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import Impl.Duong.StaffImplement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Staff;

/**
 *
 * @author DuongPhan
 */
public class Manager_getStaffDetails_Servlet extends HttpServlet {
static StaffImplement staffImplement = new StaffImplement();
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
            out.println("<title>Servlet Manager_getStaffDetails_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Manager_getStaffDetails_Servlet at " + request.getContextPath() + "</h1>");
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
        
        String accountIdStr = request.getParameter("accountId");
        System.out.println("accountId:,"+ accountIdStr);
                
        
        if (accountIdStr == null || accountIdStr.trim().isEmpty()) {
            // Xử lý khi accountId không tồn tại
            response.setContentType("text/plain");
            response.getWriter().write("Account ID is required");
            return;
        }
        
        int accountId = 0;
        try {
            accountId = Integer.parseInt(accountIdStr);
        } catch (NumberFormatException e) {
            // Xử lý khi accountId không phải là số nguyên
            response.setContentType("text/plain");
            response.getWriter().write("Invalid Account ID format");
            return;
        }
      
      
        Staff staff = staffImplement.getStaffById(accountId);
        
        
        if (staff == null) {
            response.setContentType("text/plain");
            response.getWriter().write("{\"error\": \"Staff not found for ID: " + accountId + "\"}");
            return;
        }

        
   response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            out.print("{");
            out.print("\"accountId\": " + staff.getAccount().getAccount_id() + ",");
            out.print("\"firstName\": \"" + staff.getAccount().getFirstName() + "\",");
            out.print("\"lastName\": \"" + staff.getAccount().getLastName() + "\",");
            out.print("\"email\": \"" + staff.getAccount().getEmail() + "\",");
            out.print("\"phone\": \"" + staff.getAccount().getPhone_numb() + "\",");
            out.print("\"gender\": \"" + staff.getAccount().getGender() + "\",");
            out.print("\"cccdNumber\": \"" + staff.getAccount().getCccd_numb() + "\",");
            out.print("\"dob\": \"" + staff.getAccount().getDob_Database() + "\",");
            out.print("\"postOfficeID\": \"" + staff.getPost_office().getPost_office_id() + "\",");
            out.print("\"postOfficeValue\": \"" + staff.getPost_office().getPost_office_id() + "\",");
            out.print("\"staffAvatar\": \"" + staff.getAccount().getAvatar() + "\"");
            out.print("}");

            out.flush();

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
