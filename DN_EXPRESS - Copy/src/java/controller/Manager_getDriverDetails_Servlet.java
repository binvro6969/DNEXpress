package controller;

import Impl.Duong.DriverImplement;
import context.DBcontext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Driver;

public class Manager_getDriverDetails_Servlet extends HttpServlet {
static DriverImplement driverImplement = new DriverImplement();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accountIdStr = request.getParameter("accountId");
        System.out.println("accountId:,"+ accountIdStr);
                
        
        if (accountIdStr == null || accountIdStr.trim().isEmpty()) {
            // Xử lý khi accountId không tồn tại
            response.setContentType("text/plain");
            response.getWriter().write("Account ID is required");
            return;
        }
        
        int accountId = 0;
        try {
            accountId = Integer.parseInt(accountIdStr);
        } catch (NumberFormatException e) {
            // Xử lý khi accountId không phải là số nguyên
            response.setContentType("text/plain");
            response.getWriter().write("Invalid Account ID format");
            return;
        }
      
     
        Driver driver = driverImplement.getDriverByID(accountId);
        
        
        if (driver == null) {
            response.setContentType("text/plain");
            response.getWriter().write("Driver not found for ID: " + accountId);
            return;
        }
        
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        out.print("{");
        out.print("\"accountId\": " + driver.getAccount().getAccount_id() + ",");
        out.print("\"firstName\": \"" + driver.getAccount().getFirstName()+ "\",");
        out.print("\"lastName\": \"" + driver.getAccount().getLastName() + "\",");
        out.print("\"email\": \"" + driver.getAccount().getEmail() + "\",");
        out.print("\"phone\": \"" + driver.getAccount().getPhone_numb() + "\",");
        out.print("\"gender\": \"" + driver.getAccount().getGender() + "\",");
        out.print("\"cccdNumber\": \"" + driver.getAccount().getCccd_numb() + "\",");
        out.print("\"dob\": \"" + driver.getAccount().getDob_Database()+ "\",");
        out.print("\"licenseNumber\": \"" + driver.getLicense_numb() + "\",");
        out.print("\"driverTypeID\": \"" + driver.getDriver_type().getDriv_type_id() + "\",");
        out.print("\"driverTypeValue\": \"" + driver.getDriver_type().getDriv_type_value() + "\",");
        out.print("\"postOfficeID\": \"" + driver.getPost_office().getPost_office_id() + "\",");
        out.print("\"driverAvatar\": \"" + driver.getAccount().getAvatar() + "\"");
        out.print("}");
        
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
