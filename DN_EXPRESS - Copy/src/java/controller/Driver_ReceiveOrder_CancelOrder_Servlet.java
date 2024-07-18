/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.An.DriverImplement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Driver;
import model.Shipment_Order;

/**
 *
 * @author haian
 */
public class Driver_ReceiveOrder_CancelOrder_Servlet extends HttpServlet {
    static DriverImplement driverImplement = new DriverImplement();
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
        String order_id = request.getParameter("id");
        HttpSession session = request.getSession();
        Account driver = (Account) session.getAttribute("loginSession");
        if(driver==null){
            response.sendRedirect("Authentication_LoginMain_Servlet");
        }else{
            int driver_id = driver.getAccount_id();
            //lấy địa chỉ pickup
            Shipment_Order shipment_Order = driverImplement.getOriginAddressForchooseDriver(Integer.parseInt(order_id));
            //gọi địa chỉ ra
            String apartment_number = shipment_Order.getPick_up_apartment_number();
            String street_name=shipment_Order.getPick_up_street_name();
            String district=shipment_Order.getPick_up_district();
            String ward=shipment_Order.getPick_up_ward();
            String city=shipment_Order.getPick_up_city();
            //Ghép địa chỉ
            String address=apartment_number+","+street_name+","+district+","+ward+","+city ;
            //Chọn ra driver mới
            Driver driver_change=driverImplement.chooseDriverWhenCancel(address, city, driver_id);
            int driver_id_check= driver_change.getAccount().getAccount_id();
            //Thay driver
            driverImplement.changedriverWhenCancel(driver_id_check, Integer.parseInt(order_id));
            response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
            
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
