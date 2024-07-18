/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Dinh.StaffImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Post_Office;
import model.Staff;

/**
 *
 * @author dangq
 */
public class Svl_EditProfile_Staff extends HttpServlet {

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
            out.println("<title>Servlet Svl_EditProfile_Staff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Svl_EditProfile_Staff at " + request.getContextPath() + "</h1>");
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
        StaffImplement infoDAO = new StaffImplement();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int id = account.getAccount_id(); //Nhập AccountID ở chỗ này
        Staff info = infoDAO.viewStaffInfo(id);

        List<Post_Office> listOffice = infoDAO.getAllPostOffices();
        request.setAttribute("postOffices", listOffice);
        request.setAttribute("info", info);

        Date dob = info.getAccount().getDob(); // Get the date from your data source

        if (dob != null) {
            // Format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatted = dateFormat.format(dob);
            request.setAttribute("dobFormatted", dateFormatted);
        }

        request.getRequestDispatcher("Scr_EditProfile_EditProfileStaff.jsp").forward(request, response);
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

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        String genderStr = request.getParameter("select-gen");
        char gender = ' ';
        if ("Female".equals(genderStr)) {
            gender = 'F';
        } else if ("Male".equals(genderStr)) {
            gender = 'M';
        } else {
            gender = 'O';
        }

        String dobStr = request.getParameter("dob");
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String cccdNumber = request.getParameter("idNumber");
        int poId = Integer.parseInt(request.getParameter("post-office"));
        StaffImplement infoDAO = new StaffImplement();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int id = account.getAccount_id();

        account.setFirstName(fname);
        account.setLastName(lname);
        account.setEmail(email);
        account.setPhone_numb(phone);
        account.setDob(dob);
        account.setCccd_numb(cccdNumber);
        account.setGender(gender);
        Post_Office po = new Post_Office();
        po.setPost_office_id(poId);
        Staff staff = new Staff(po, account);

        String msg = "";
        if (infoDAO.updateStaffInfo(staff)) {
            msg = "Update success!";
        } else {
            msg = "Update fail!";
        }

        session.setAttribute("msg", msg);
        response.sendRedirect("Svl_Profile_Staff");

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
