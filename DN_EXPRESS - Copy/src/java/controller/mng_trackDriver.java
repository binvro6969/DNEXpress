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
import model.Driver;
import model.Post_Office;

/**
 *
 * @author MINHTRUNG
 */
public class mng_trackDriver extends HttpServlet {
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
            out.println("<title>Servlet mng_trackDriver</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mng_trackDriver at " + request.getContextPath() + "</h1>");
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
        String mgs = "Enter information to track driver";
        request.setAttribute("msg", mgs);
        request.getRequestDispatcher("mng_trackingDriver.jsp").forward(request, response);
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
        
            ArrayList<Driver> driverList = new ArrayList<>();
            Post_Office po_off = new Post_Office();

            String id = null;
            String name = null;
            String postId = null;

            String searchIndex = request.getParameter("searchIndex");
            String selected = request.getParameter("selected");

            switch (selected) {
                case "byId":
                    id = searchIndex;
                    break;
                case "byName":
                    name = searchIndex;
                    break;
                case "byPostOffice":
                    postId = searchIndex;
                    break;
                default:            
                    System.out.println("Invalid selection");
                    break;
            }

            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("postId: " + postId);

            driverList = driverImplement.getDriver(id, name, postId);         
            System.out.println(driverList);
            
            
            

            request.setAttribute("driverList", driverList);

            String mgs = "No driver can be found";
            request.setAttribute("msg", mgs);
            request.getRequestDispatcher("mng_trackingDriver.jsp").forward(request, response);
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
