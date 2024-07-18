/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.An.DriverImplement;
import Impl.An.GeneralImplement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order_Status;
import model.Post_Office;
import model.Shipment_Order;

/**
 *
 * @author haian
 */
public class Driver_ReceiveOrder_UpdateOrderStatus_Servlet extends HttpServlet {
    static DriverImplement driverImplement = new DriverImplement();    
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
        String order_id = request.getParameter("id"); 
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        Order_Status order_Status = driverImplement.getOrderStatus(Integer.parseInt(order_id));       
        boolean check_InProvince = driverImplement.checkInProvince(Integer.parseInt(order_id));
        if (check_InProvince == false) {
        //Xử lí ngoại tỉnh
        if(order_Status.getStatus().equals("Picking up")){           
           
            if(account == null){
                response.sendRedirect("Authentication_LoginMain_Servlet");
            }else{
                int driver_id=account.getAccount_id();
                driverImplement.updateOrderStatusSenderInterProvince(Integer.parseInt(order_id),driver_id);                                      
                response.sendRedirect("Driver_ReceiveOrder_DetailOrder_Servlet?id="+order_id);
            }
            
        }
        if(order_Status.getStatus().equals("In Transit")){
            if(account == null){
                response.sendRedirect("Authentication_LoginMain_Servlet");
            }else{
                driverImplement.updateOrderStatusIntransitInterProvince(Integer.parseInt(order_id));
                driverImplement.updateIsWorkingOff(account.getAccount_id());
                response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
            }                      
        }
               
        if(order_Status.getStatus().equals("In Transit-2")){                                                                 
            //Xử lý Post_office receiver    
            driverImplement.updateOrderStatusInTransit2InterProvince(Integer.parseInt(order_id));
            response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
            
        }      
        if(order_Status.getStatus().equals("Delivering")){
            if(account == null){
                response.sendRedirect("Authentication_LoginMain_Servlet");
            }else{
                driverImplement.updateOrderStatusDeliveringInterProvince(Integer.parseInt(order_id));
                driverImplement.updateIsWorkingOff(account.getAccount_id());
                response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
            }                                                                                            
        }
        
        }else{
        //Xử lí nội tỉnh            
            if(account == null){
                response.sendRedirect("Authentication_LoginMain_Servlet");
            } else {
                int driver_id= account.getAccount_id();
                if (order_Status.getStatus().equals("Picking up")) {
                    driverImplement.updateOrderStatusSenderInProvince(Integer.parseInt(order_id),driver_id);
                    response.sendRedirect("Driver_ReceiveOrder_DetailOrder_Servlet?id=" + order_id);
                }
                if (order_Status.getStatus().equals("Delivering")) {
                    driverImplement.updateOrderStatusDeliveringInProvince(Integer.parseInt(order_id),driver_id);
                    driverImplement.updateIsWorkingOff(driver_id);
                    response.sendRedirect("Driver_ReceiveOrder_ListOrder_Servlet");
                }
            }                                                   
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
