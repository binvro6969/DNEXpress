/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.An.CustomerImplement;
import Impl.An.DriverImplement;
import Impl.An.GeneralImplement;
import Impl.An.ManagerImplement;
import Impl.An.StaffImplement;
import google_context.UserEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author haian
 */
public class Authentication_ChangePasswornd_ChangePassword_Servlet extends HttpServlet {
    static CustomerImplement customerImplement = new CustomerImplement();
    static DriverImplement driverImplement = new DriverImplement();
    static StaffImplement staffImplement = new StaffImplement();
    static ManagerImplement managerImplement = new ManagerImplement();
    static GeneralImplement generalImplement = new GeneralImplement();
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
            out.println("<title>Servlet Authentication_ChangePasswornd_ChangePassword_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentication_ChangePasswornd_ChangePassword_Servlet at " + request.getContextPath() + "</h1>");
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
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        if(newPassword.equals(confirmPassword)){
            HttpSession session = request.getSession();
            UserEmail userEmail = (UserEmail) session.getAttribute("authcode");
            String email = userEmail.getAccount().getEmail();
            Account account = new Account();
            account.setEmail(email);
            List<Account> list = new ArrayList<>();
            list = generalImplement.findByEmail(account);
            int account_id = list.get(0).getAccount_id();
            Account accountChangPass= new Account ();
            accountChangPass.setAccount_id(account_id);
            accountChangPass.setPassword(newPassword);
//            account_id, newPassword
            if (generalImplement.changePassword(accountChangPass)) {
                account.setPassword(newPassword);
                session.setAttribute("loginSession", account);
                request.getRequestDispatcher("Scr_ForgotPassword_Success.jsp").forward(request, response);
            }
        } else {
            String msg = "nhập lại mật khẩu không khớp";
            request.setAttribute("message", msg);
            request.getRequestDispatcher("Scr_ForgotPassword_ChangePass.jsp").forward(request, response);
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
