/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Dinh.GeneralImplement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;

/**
 *
 * @author dangq
 */
@MultipartConfig
public class Svl_EditProfile_UploadImg extends HttpServlet {

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
            out.println("<title>Servlet Svl_EditProfile_Customer_UploadImg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Svl_EditProfile_Customer_UploadImg at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int id = account.getAccount_id(); // Lấy Account_ID từ session hoặc từ request

        //Upload image
        Part filePart = request.getPart("file");
        String uploadDir = "./img/upload";

        if (filePart != null && filePart.getSize() > 0) {

            String documentName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            String[] allowedExtensions = {"jpg", "jpeg", "png"};
            String fileExtension = documentName.substring(documentName.lastIndexOf(".") + 1).toLowerCase();

            if (Arrays.asList(allowedExtensions).contains(fileExtension)) {

            } else {
                response.getWriter().println("Invalid file type. Allowed types: jpg, jpeg, png.");
                return;
            }

            String filePath = uploadDir + "/" + documentName;
            account.setAvatar(filePath);

            try (InputStream inputStream = filePart.getInputStream()) {
                String realPath = request.getServletContext().getRealPath(uploadDir);
                File uploadPath = new File(realPath);
                if (!uploadPath.exists()) {
                    uploadPath.mkdir();
                }
                Files.copy(inputStream, Paths.get(realPath, documentName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            response.getWriter().println(filePath);
            System.out.println(filePath);

        } else {
//            response.getWriter().println("No file uploaded.");
            System.out.println("No file uploaded.");
        }

        GeneralImplement infoDAO = new GeneralImplement();
//    fname, lname, email, phone, dob, cccdNumber, gender
        String msg = "";
        if (infoDAO.updateAvatar(account)) {
            msg = "Update success!";
        } else {
            msg = "Update fail!";
        }

        session.setAttribute("msg", msg);
        // Determine the referer and redirect accordingly
        String referer = request.getHeader("referer");
        if (referer != null) {
            if (referer.contains("Svl_EditProfile_Customer")) {
                response.sendRedirect("Svl_Profile_Customer");
            } else if (referer.contains("Svl_EditProfile_Driver")) {
                response.sendRedirect("Svl_Profile_Driver");
            } else if (referer.contains("Scr_EditProfile_EditProfileManager")) {
                response.sendRedirect("Svl_Profile_Manager");
            } else if (referer.contains("Scr_EditProfile_EditProfileStaff")) {
                response.sendRedirect("Svl_Profile_Staff");
            } else {
                // Default redirection if none of the conditions match
                response.sendRedirect("Authentication_LoginMain_Servlet");
            }
        } else {
             response.sendRedirect("Authentication_LoginMain_Servlet");
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
