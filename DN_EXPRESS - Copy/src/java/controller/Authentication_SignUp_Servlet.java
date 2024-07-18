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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author haian
 */
public class Authentication_SignUp_Servlet extends HttpServlet {
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
            out.println("<title>Servlet Authentication_SignUp_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentication_SignUp_Servlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Scr_signUp_signUp.jsp").forward(request, response);
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
        List<Account> list = new ArrayList<>();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone_numb = request.getParameter("phone_numb");
        String email = request.getParameter("email");
        String password = request.getParameter("password");        
        int role = 1;              
        Account account = new Account();    
        account.setFirstName(fname);
        account.setLastName(lname);
        account.setEmail(email);
        account.setPassword(password);
        account.setPhone_numb(phone_numb);
        account.setRole(role);
        list=generalImplement.findByEmail(account);
        if(list.isEmpty()){
            generalImplement.addNew(account);
            request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
        }else{
            String msg = "This Email is exist!";
            request.setAttribute("message", msg);
            request.getRequestDispatcher("Scr_signUp_signUp.jsp").forward(request, response);
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
