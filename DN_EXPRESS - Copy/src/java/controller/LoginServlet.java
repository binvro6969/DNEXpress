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
import google_context.GooglePojo;
import google_context.GoogleUtils;
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
public class LoginServlet extends HttpServlet {
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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Account> listUser =new ArrayList<>(); 
        String code = request.getParameter("code");
        String accessToken = GoogleUtils.getToken(code);
        GooglePojo user = GoogleUtils.getUserInfo(accessToken);
        System.out.println(user);
        String email=user.getEmail();       
        //CHECK TÀI KHOẢN ĐÃ CÓ TRONG DATABASE CHƯA                      
        Account account = new Account();
        account.setEmail(email);      
        listUser  = generalImplement.findByEmail(account);
        
        
        //list trống thì thêm tài khoản vào database
        if(listUser.isEmpty()){
            generalImplement.addnewAccountWithGoogle(account);
            HttpSession session = request.getSession();
            listUser  = generalImplement.findByEmail(account);
            int account_id = listUser.get(0).getAccount_id();
            account.setAccount_id(account_id);
            session.setAttribute("loginSession", account);
            request.getRequestDispatcher("Customer_Statistic_Jsp_Servlet").forward(request, response);
        }else{
            int account_id = listUser.get(0).getAccount_id();
            account.setAccount_id(account_id);
            HttpSession session = request.getSession();
            session.setAttribute("loginSession", account);
            response.sendRedirect("Customer_Statistic_Jsp_Servlet");
        }
                           
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
