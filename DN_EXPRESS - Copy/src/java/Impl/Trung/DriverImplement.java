/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Trung;

import Impl.An.*;
import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Driver;
import model.Driver_type;
import model.Post_Office;
import model.Vehicle;
import model.Vehicle_type;

/**
 *
 * @author haian
 */
public class DriverImplement implements IRepository<Driver>{
//khai bao CustomerImplement và sử dụng có thể thêm các method khác không chỉnh sửa IRepository<>
    @Override
    public List<Driver> display(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//

    @Override
    public List<Driver> find(Driver driver) {
        String sqlQuerry_find ="SELECT \n" +
                               "    a.Account_ID,\n" +
                               "    a.Lname,\n" +
                               "    a.Fname,\n" +
                               "    a.Phone,\n" +
                               "    a.Password,\n" +
                               "    a.Role_id,\n" +
                               "    a.Email,\n" +
                               "    d.driver_type_id,\n" +
                               "    d.Status\n" +
                               "FROM \n" +
                               "    Account a\n" +
                               "JOIN \n" +
                               "    Driver d ON a.Account_ID = d.Driver_ID\n" +
                               "where Phone =? and Password =? and Role_id=?;";
        List<Driver> list = new ArrayList<>();        
        String phone_numb_check= driver.getAccount().getPhone_numb();
        String pass=driver.getAccount().getPassword();
        int role_check=driver.getAccount().getRole();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, phone_numb_check);
            ps.setString(2, pass);
            ps.setInt(3, role_check);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt(1);
                String lname=rs.getString(2);
                String fname=rs.getString(3);                
                String phone_numb =rs.getString(4);               
                String password=rs.getString(5);               
                int role =rs.getInt(6);
                String email=rs.getString(7);
                int driver_type_id=rs.getInt(8);
                boolean status = rs.getBoolean(9);
                Driver_type driver_type = new Driver_type(driver_type_id);
                Account account = new Account(account_id, fname, lname, email, password, phone_numb, role);
                driver = new Driver(account,driver_type, status);
                list.add(driver);        
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");          
        }
       return list;       
    }
    
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//

    @Override
    public Boolean checkIdIsExist(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//
    public boolean updateActiveStatusOn(Driver driver){
        String SQLquery="UPDATE Driver set Status=1 where Account_ID=?;";
        try {
            int id_Driver = driver.getAccount().getAccount_id();
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquery);
            ps.setInt(1, id_Driver);
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected!=0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return false;
    }
    
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//
    public boolean updateActiveStatusOff(Driver driver){
        String SQLquerry="UPDATE Driver set Status=0 where Account_ID=?;";
        try {
            int id_Driver = driver.getAccount().getAccount_id();           
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquerry);
            ps.setInt(1, id_Driver);
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected!=0) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return true;
    }
    
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//
    
     public ArrayList<Driver> getDriver(String id, String name, String postId) {
        GeneralImplement ger = new GeneralImplement();
        ArrayList<Driver> drivers = new ArrayList<>();
        String sqlQuery = "SELECT a.Account_ID, a.Lname, a.Fname, a.Phone, a.Password, a.Role_id, a.Email, a.CCCD_number, a.Avatar, a.Gender, "
                         + "d.Driver_Type_ID, d.Online_Status, d.Driver_ID, d.License_number, d.Rating, d.Longitude, d.Latitude, d.Post_Office_ID, a.Dob, a.Status, "
                         + "p.Post_Office_ID, p.Apartment_Number, p.Street_Name, p.District, p.Ward, p.City, "
                         + "dt.Driver_Type_ID, dt.Driver_Type_Value "
                         + "FROM Account a "
                         + "JOIN Driver d ON a.Account_ID = d.Driver_ID "
                         + "JOIN Post_Office p ON d.Post_Office_ID = p.Post_Office_ID "
                         + "JOIN Driver_Type dt ON d.Driver_Type_ID = dt.Driver_Type_ID ";

        PreparedStatement ps = null;

        try {
            Connection conn = new DBcontext().getConnection();

            if (id != null) {
                sqlQuery += "WHERE a.Account_ID = ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, id);
            } else if (name != null) {
                sqlQuery += "WHERE a.Fname = ? OR a.Lname = ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, name);
                ps.setString(2, name);
            } else if (postId != null) {
                sqlQuery += "WHERE d.Post_Office_ID = ?";
                ps = conn.prepareStatement(sqlQuery);
                ps.setString(1, postId);
            } else {
                return drivers;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt(1);
                String lname = rs.getString(2);
                String fname = rs.getString(3);
                String phone = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String email = rs.getString(7);
                String cccd_numb = rs.getString(8);
                String avatar = rs.getString(9);
                char gender = rs.getString(10).charAt(0);
                int driverTypeId = rs.getInt(11);
                boolean status = rs.getBoolean(12);
                int driver_id = rs.getInt(13);
                String license_numb = rs.getString(14);
                float rating = rs.getFloat(15);
                float longitude = rs.getFloat(16);
                float latitude = rs.getFloat(17);
                int postOfficeID = rs.getInt(18);
                Date dob = rs.getDate(19);
                boolean statusAcc = rs.getBoolean(20);

                // Fetching Post_Office information
                int postOfficeId = rs.getInt(21);
                String apartmentNumber = rs.getString(22);
                String streetName = rs.getString(23);
                String district = rs.getString(24);
                String ward = rs.getString(25);
                String city = rs.getString(26);

                Post_Office postOffice = new Post_Office(postOfficeId, apartmentNumber, streetName, district, ward, city);

                // Fetching Driver_Type information
                String driverTypeValue = rs.getString(28);
                
               
                Driver_type driverType = new Driver_type(driverTypeId, driverTypeValue);
                
  
                Account account = new Account(accountId, fname, lname, email, password, phone, dob, cccd_numb, avatar, gender, roleId, statusAcc);
                Driver driver = new Driver( account, license_numb, driverType, status, latitude, longitude, rating, postOffice);
                drivers.add(driver);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return drivers;
    }
     
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//
    public ArrayList<Vehicle> getVehicleByDriverID(String driverId) {
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    String sqlQuery = "SELECT v.Vehicle_ID, v.Driver_ID, v.Vehicle_Type_ID, v.Plate_Number, v.Brand, v.Vehicle_Img, vt.Vehicle_Type_Value "
                     + "FROM Vehicle v "
                     + "JOIN Vehicle_Type vt ON v.Vehicle_Type_ID = vt.Vehicle_Type_ID "
                     + "WHERE v.Driver_ID = ?";

    PreparedStatement ps = null;

    try {
        Connection conn = new DBcontext().getConnection();
        ps = conn.prepareStatement(sqlQuery);
        ps.setString(1, driverId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int vehicleId = rs.getInt(1);
            int driverIdResult = rs.getInt(2);
            int vehicleTypeId = rs.getInt(3);
            String plateNumber = rs.getString(4);
            String brand = rs.getString(5);
            String vehicleImg = rs.getString(6);
            String vehicleTypeValue = rs.getString(7);
            
            //-------------------------------------------//
            Account account = new Account();
            account.setAccount_id(vehicleId);          
            //-----------------------------------------------//
             Driver driver = new Driver();
             driver.setAccount(account);

           
            Vehicle_type vehicle_type = new Vehicle_type(vehicleTypeId,vehicleTypeValue);

            Vehicle vehicle = new Vehicle( driver, vehicle_type, plateNumber, brand, vehicleImg);
            vehicles.add(vehicle);
        }
        conn.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }   catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    return vehicles;
}

    

     
     
    //---------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------//
     
     public static void main(String[] args) {
        DriverImplement driverImplement = new DriverImplement();

        // Test by ID
        System.out.println("Testing getDriver by ID:");
        List<Driver> driversById = driverImplement.getDriver("4", null, null);
        for (Driver driver : driversById) {
            System.out.println(driver);
        }

        // Test by Name
        System.out.println("\nTesting getDriver by Name:");
        List<Driver> driversByName = driverImplement.getDriver(null, "Anh", null);
        for (Driver driver : driversByName) {
            System.out.println(driver);
        }

        // Test by Post ID
        System.out.println("\nTesting getDriver by Post ID:");
        List<Driver> driversByPostId = driverImplement.getDriver(null, null, "1");
        for (Driver driver : driversByPostId) {
            System.out.println(driver);
        }
        
        System.out.println("\nTesting getVehical by Drv ID:");
        List<Vehicle> vehicalById = driverImplement.getVehicleByDriverID("4");
        for (Vehicle vehicle : vehicalById) {
            System.out.println(vehicle);
        }
    }
}
