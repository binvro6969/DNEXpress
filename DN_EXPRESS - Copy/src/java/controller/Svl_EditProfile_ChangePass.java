/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Dinh.CustomerImplement;
import Impl.Dinh.GeneralImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author dangq
 */
public class Svl_EditProfile_ChangePass extends HttpServlet {

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
            out.println("<title>Servlet Svl_EditProfile_ChangePass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Svl_EditProfile_ChangePass at " + request.getContextPath() + "</h1>");
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
        GeneralImplement generalImpl = new GeneralImplement();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int id = account.getAccount_id(); //Nhập AccountID ở chỗ này
        Account info = generalImpl.viewAccountinfo(id);
        request.setAttribute("info", info);

        Date dob = info.getDob(); // Get the date from your data source

        if (dob != null) {
            // Format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatted = dateFormat.format(dob);
            request.setAttribute("dobFormatted", dateFormatted);
        }
        int role = account.getRole();

        switch (role) {
            case 1:
                request.getRequestDispatcher("Scr_EditProfle_ChangePasswordCustomer.jsp").forward(request, response);
                break;
            case 2:
                request.getRequestDispatcher("Scr_EditProfle_ChangePasswordDriver.jsp").forward(request, response);
                break;
            case 3:
                request.getRequestDispatcher("Scr_EditProfle_ChangePasswordStaff.jsp").forward(request, response);
                break;
            case 4:
                request.getRequestDispatcher("Scr_EditProfle_ChangePasswordManager.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("Authentication_LoginMain_Servlet");
                break;
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
        GeneralImplement generalImpl = new GeneralImplement();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");

        String oldPassword = request.getParameter("oldPass");
        String newPassword = request.getParameter("newPass");
        String confirmPassword = request.getParameter("confirmPass");
        String msg = "";

        if (oldPassword.equals(account.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                if (generalImpl.updatePassword(account.getAccount_id(), newPassword)) {
                    session.invalidate();
                    request.getRequestDispatcher("Scr_ForgotPassword_Success.jsp").forward(request, response);
                } else {
                    msg = "Password Updating Failed";
                }
            } else {
                msg = "Password mismatched";
            }
        } else {
            msg = "Incorrect old password";
        }
        request.setAttribute("msg", msg);

        doGet(request, response);

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
