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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Driver;
import model.Driver_type;

/**
 *
 * @author haian
 */
public class Authentication_LoginMain_Servlet extends HttpServlet {
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
            out.println("<title>Servlet Authentication_LoginMain_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Authentication_LoginMain_Servlet at " + request.getContextPath() + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        String number = null;
        String pwd =null;
//        for (Cookie o : cookies){
//            if(o.getName().equals("Account")){
//                number=o.getValue();
//                request.setAttribute("number", number);
//            
//            }
//            if (o.getName().equals("pass")) {
//                pwd = o.getValue();
//                request.setAttribute("pass",pwd);
//            }
//        }       
        request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String role = request.getParameter("role");
        String remember=request.getParameter("remember");
        String phone_numb = request.getParameter("phoneNumber");
        String password =  request.getParameter("password");
        List<Account> listAcc = new ArrayList<>();               
        Account account = new Account();
//        password, phone_numb,Integer.parseInt(role)
        account.setPassword(password);
        account.setPhone_numb(phone_numb);
        account.setRole(Integer.parseInt(role));
        listAcc=generalImplement.find(account);
        if(role==null){
            String msg="vui lòng chọn user hoặc shipper";
            request.setAttribute("MSG_Login", msg);
            request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);    
        }else{
            //login cho Customer
            if(Integer.parseInt(role)==1){              
                if(listAcc.isEmpty()){
                    String msg = "Sai Số điện thoại hoặc mật khẩu!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
                }else{
                    if (remember != null) {
                            Cookie cookieU = new Cookie("Account", phone_numb);
                            cookieU.setMaxAge(7 * 24 * 60 * 60);
                            response.addCookie(cookieU);

                            Cookie cookieP = new Cookie("pass", password);
                            cookieP.setMaxAge(7 * 24 * 60 * 60);    
                            response.addCookie(cookieP);
                    }
                    HttpSession session = request.getSession();
                    String fistName=listAcc.get(0).getFirstName();
                    String lastName=listAcc.get(0).getLastName();
                    String email = listAcc.get(0).getEmail();
                    int account_id = listAcc.get(0).getAccount_id();
                    Account accountLogin = new Account();
//                  account_id, fistName, lastName, email, password, phone_numb, 
                    accountLogin.setAccount_id(account_id);
                    accountLogin.setFirstName(fistName);
                    accountLogin.setLastName(lastName);
                    accountLogin.setEmail(email);
                    accountLogin.setPassword(password);
                    accountLogin.setPhone_numb(phone_numb);
                    accountLogin.setRole(Integer.parseInt(role));
                    session.setAttribute("loginSession", accountLogin);                   
                    response.sendRedirect("Customer_Statistic_Jsp_Servlet");
                }
            }
            //login cho Driver
            if(Integer.parseInt(role)==2){
                List<Driver> listDriver = new ArrayList<>();
                Driver driver = new Driver(account);
                listDriver = driverImplement.find(driver);
                if(listDriver.isEmpty()){
                    String msg = "Sai Số điện thoại hoặc mật khẩu!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
                }else{
                    if (remember != null) {
                            Cookie cookieU = new Cookie("Account", phone_numb);
                            cookieU.setMaxAge(7 * 24 * 60 * 60);
                            response.addCookie(cookieU);

                            Cookie cookieP = new Cookie("pass", password);
                            cookieP.setMaxAge(7 * 24 * 60 * 60);    
                            response.addCookie(cookieP);
                    }
                    HttpSession session = request.getSession();
                    String fistName=listDriver.get(0).getAccount().getFirstName();
                    String lastName=listDriver.get(0).getAccount().getLastName();
                    String name= fistName+" "+lastName;
                    int account_id = listDriver.get(0).getAccount().getAccount_id();
                    String email = listAcc.get(0).getEmail();
                    boolean status = listDriver.get(0).isStatus();
                    int driver_type_id = listDriver.get(0).getDriver_type().getDriv_type_id();
                    Driver_type driver_type = new Driver_type(driver_type_id);
                    Account accountLogin = new Account ();
//                    account_id, fistName, lastName, email, password, phone_numb, Integer.parseInt(role)
                    accountLogin.setAccount_id(account_id);
                    accountLogin.setFirstName(fistName);
                    accountLogin.setLastName(lastName);
                    accountLogin.setEmail(email);
                    accountLogin.setPassword(password);
                    accountLogin.setPhone_numb(phone_numb);
                    accountLogin.setRole(Integer.parseInt(role));
                    Driver driverlogin = new Driver(accountLogin, driver_type, status);
                    //Set trạng thái hoạt động cho driver
                    if(status==false){
                        if(driverImplement.updateActiveStatusOn(driverlogin)==true){
                            driverlogin.setStatus(true);
                        }
                    }
                    session.setAttribute("loginSession", accountLogin);
                    session.setAttribute("driverLoginSession", driverlogin);                                    
                    response.sendRedirect("Driver_Statistic_Jsp_Servlet");
                }
            }
            //login cho Staff
            if(Integer.parseInt(role)==3){
                if(listAcc.isEmpty()){
                    String msg = "Sai Số điện thoại hoặc mật khẩu!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
                }else{
                    if (remember != null) {
                            Cookie cookieU = new Cookie("Account", phone_numb);
                            cookieU.setMaxAge(7 * 24 * 60 * 60);
                            response.addCookie(cookieU);

                            Cookie cookieP = new Cookie("pass", password);
                            cookieP.setMaxAge(7 * 24 * 60 * 60);    
                            response.addCookie(cookieP);
                    }
                    HttpSession session = request.getSession();
                    String fistName=listAcc.get(0).getFirstName();
                    String lastName=listAcc.get(0).getLastName();
                    String email = listAcc.get(0).getEmail();
                    int account_id = listAcc.get(0).getAccount_id();
                    Account accountLogin = new Account();
                    accountLogin.setAccount_id(account_id);
                    accountLogin.setFirstName(fistName);
                    accountLogin.setLastName(lastName);
                    accountLogin.setEmail(email);
                    accountLogin.setPassword(password);
                    accountLogin.setPhone_numb(phone_numb);
                    accountLogin.setRole(Integer.parseInt(role));
                    session.setAttribute("loginSession", accountLogin);
                    response.sendRedirect("Svl_Profile_Staff");
                }
            }
            //login cho Manager
            if(Integer.parseInt(role)==4){
                if(listAcc.isEmpty()){
                    String msg = "Sai Số điện thoại hoặc mật khẩu!";
                    request.setAttribute("message", msg);
                    request.getRequestDispatcher("Scr_Login_login.jsp").forward(request, response);
                }else{
                    if (remember != null) {
                            Cookie cookieU = new Cookie("Account", phone_numb);
                            cookieU.setMaxAge(7 * 24 * 60 * 60);
                            response.addCookie(cookieU);

                            Cookie cookieP = new Cookie("pass", password);
                            cookieP.setMaxAge(7 * 24 * 60 * 60);    
                            response.addCookie(cookieP);
                    }
                    HttpSession session = request.getSession();
                    String fistName=listAcc.get(0).getFirstName();
                    String lastName=listAcc.get(0).getLastName();
                    int account_id = listAcc.get(0).getAccount_id();
                    String email = listAcc.get(0).getEmail();
                    Account accountLogin = new Account();
                    accountLogin.setAccount_id(account_id);
                    accountLogin.setFirstName(fistName);
                    accountLogin.setLastName(lastName);
                    accountLogin.setEmail(email);
                    accountLogin.setPassword(password);
                    accountLogin.setPhone_numb(phone_numb);
                    accountLogin.setRole(Integer.parseInt(role));
                    session.setAttribute("loginSession", accountLogin);                   
                    response.sendRedirect("mng_ManagePostOffice");
                }
            }
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
