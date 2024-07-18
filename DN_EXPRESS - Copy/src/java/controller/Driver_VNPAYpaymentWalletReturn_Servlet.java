package controller;

import Impl.Duong.DriverImplement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

public class Driver_VNPAYpaymentWalletReturn_Servlet extends HttpServlet {

    static DriverImplement driverImplement = new DriverImplement();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int driverId = account.getAccount_id();
        if (session.getAttribute("loginSession") == null) {
            response.sendRedirect("Authentication_LoginMain_Servlet");
        } else {
            String transactionStatus = request.getParameter("vnp_TransactionStatus");

            if ("00".equals(transactionStatus)) {
               
                int amount = Integer.parseInt(request.getParameter("vnp_Amount")) / 100;
                driverImplement.updateBalance(driverId, amount);
            }
            // Redirect to view wallet page
            response.sendRedirect("Driver_ViewDriverWallet_Servlet");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
