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
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Delivery_Type;
import model.Driver;
import model.Import_Export_Ticket;
import model.Invoice;
import model.Manager;
import model.Order_Status;
import model.Package_Detail;
import model.Package_Size;
import model.Package_Type;
import model.Payment_Method;
import model.Post_Office;
import model.Shipment_Order;
import model.Shipment_Order_Driver;
import model.Staff;
import model.Ticket_Status;

/**
 *
 * @author haian
 */
public class GeneralImplement implements IRepository<Account>{
 static Impl.An.DriverImplement driverImplement = new Impl.An.DriverImplement();  
    public List<Account> findByEmail(Account account){
        String sqlQuerry_find ="SELECT Account_ID,Email FROM Account WHERE Email=?";
        List<Account> list = new ArrayList<>();
        String check_email= account.getEmail();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, check_email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int account_id= rs.getInt(1);
                String email = rs.getString(2);
                account.setAccount_id(account_id);
                account.setEmail(email);
                list.add(account);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        } 
        return list;
    }
    
    public void addnewAccountWithGoogle(Account account ){
        String email= account.getEmail();        
       
        String sqlQuerry_add = "insert Account(Email,Role_id) values(?,1);";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_add);
            ps.setString(1, email);
            ps.executeUpdate();
            conn.close();    
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public List<Account> display(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Account account) {
        String fname = account.getFirstName();
        String lname = account.getLastName();
        String phone_numb = account.getPhone_numb();
        String email = account.getEmail();
        String password = account.getPassword();
        int role = 1;
        String sqlQuerry_add = "insert Account(Lname,Fname,Email,Phone,Password,Role_id) values(?,?,?,?,?,?);";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_add,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,lname );
            ps.setString(2, fname);
            ps.setString(3, email);
            ps.setString(4, phone_numb);
            ps.setString(5, password);
            ps.setInt(6, role);           
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int account_ID=0;
            if (rs.next()) {
                account_ID =rs.getInt(1);
            }
            String sqlQuerry_add_cus="insert Customer (Customer_ID) values (?);";
            PreparedStatement pscus = conn.prepareStatement(sqlQuerry_add_cus);
            pscus.setInt(1, account_ID);
            pscus.executeUpdate();
            conn.close();    
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @Override
    public void edit(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Account> find(Account account) {
        
        String sqlQuerry_find ="Select Account_id,Lname,Fname,Phone,Password,Email,Role_id from Account where Phone=? and Password=? and Role_id=?";
        List<Account> list = new ArrayList<>();
        String phone_numb_check= account.getPhone_numb();
        String pass=account.getPassword();
        int role_check =account.getRole();
       try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, phone_numb_check);
            ps.setString(2, pass);
            ps.setInt(3, role_check);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int account_id=rs.getInt(1);
                String lname=rs.getString(2);
                String fname=rs.getString(3);                
                String phone_numb =rs.getString(4);               
                String password=rs.getString(5);
                String email=rs.getString(6);
                int role =rs.getInt(7);
                account = new Account(account_id, fname, lname, email, password, phone_numb, role);
                list.add(account);
                 //chuyển từ sql date sang java.until.date
//                Date dobRaw = new java.util.Date(dob_database.getTime());
//                //đổi format cho date
//                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
//                //đổi format cho date
//                String formattedDateStr = outputFormat.format(dobRaw);
//                //đổi format cho date
//                Date dob = outputFormat.parse(formattedDateStr);
//                //tạo đối tượng                
//                account = new Account(id_account, Fname, lname, emai, password, phone,dob , cccd_numb, avatar, Gender, role);
//                //thêm vào danh sách 
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");          
        }
       return list;
    }
    public boolean changePassword(Account account){
        
        try {
            String SQLquerry = "UPDATE Account set Password=? where Account_ID=?";
            String password = account.getPassword();
            int account_id= account.getAccount_id();
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquerry);
            ps.setString(1, password);
            ps.setInt(2, account_id);
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected!=0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean checkIdIsExist(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
      public Post_Office getPostOfficeByID(int postID) {
        Post_Office postOffice = null;
        String sqlQuery = "SELECT post_office_id, Apartment_Number, Street_Name, district, ward, city FROM Post_Office WHERE post_office_id = ?";
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, postID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("post_office_id");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("district");
                String ward = rs.getString("ward");
                String city = rs.getString("city");

                postOffice = new Post_Office(id, apartmentNumber, streetName, district, ward, city);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return postOffice;
    }
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
      
   public ArrayList<Shipment_Order> getShipmentOrdersByDriverId(String driverId) {
          DriverImplement driverImplement = new DriverImplement();
        ArrayList<Shipment_Order> orders = new ArrayList<>();
        String sqlQuery = "SELECT so.Order_ID, so.Customer_ID, so.Order_Date, so.Cust_Feedback, so.Cust_Driver_Rating, " +
                          "so.Pick_Up_Apartment_Number, so.Pick_Up_Street_Name, so.Pick_Up_District, so.Pick_Up_Ward, so.Pick_Up_City, " +
                          "so.Final_Receiver_Name, so.Final_Receiver_Phone, so.Final_Apartment_Number, so.Final_Street_Name, so.Final_District, " +
                          "so.Final_Ward, so.Final_City, os.Status, os.Process " +
                          "FROM Shipment_Order so " +
                          "JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                          "JOIN Order_Status os ON so.Order_ID = os.Order_ID " +
                          "WHERE sod.Driver_ID = ? AND os.Status IN ('Picking up', 'In transit', 'Delivering') AND os.Process = 'Ongoing'";

    try (Connection conn = new DBcontext().getConnection();
            
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, driverId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                
                int customerId = rs.getInt("Customer_ID");
                Customer customer = getCustomerbyCustID(String.valueOf(customerId));
                Driver driver = driverImplement.getDriver(driverId,null,null).get(0);
                Shipment_Order order = new Shipment_Order();
                
                Timestamp timestamp = rs.getTimestamp("Order_Date");
                String orderDate = timestamp != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
                
               
                order.setCustomer(customer);
                order.setOrder_id(rs.getInt("Order_ID"));
                order.setOrder_date(orderDate);
                order.setCust_driver_rating(rs.getInt("Cust_Driver_Rating"));
                order.setCust_feedback(rs.getString("Cust_Feedback"));
                order.setPick_up_apartment_number(rs.getString("Pick_Up_Apartment_Number"));
                order.setPick_up_street_name(rs.getString("Pick_Up_Street_Name"));
                order.setPick_up_district(rs.getString("Pick_Up_District"));
                order.setPick_up_ward(rs.getString("Pick_Up_Ward"));
                order.setPick_up_city(rs.getString("Pick_Up_City"));
                order.setFinal_receiver_name(rs.getString("Final_Receiver_Name"));
                order.setFinal_receiver_phone(rs.getString("Final_Receiver_Phone"));
                order.setFinal_apartment_number(rs.getString("Final_Apartment_Number"));
                order.setFinal_street_name(rs.getString("Final_Street_Name"));
                order.setFinal_district(rs.getString("Final_District"));
                order.setFinal_ward(rs.getString("Final_Ward"));
                order.setFinal_city(rs.getString("Final_City"));
                

                orders.add(order);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }   catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    return orders;
}



   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
      
    public Shipment_Order getShipmentOrdersByOrderId(String orderId) {
    DriverImplement driverImplement = new DriverImplement();
    Shipment_Order order = new Shipment_Order();
    String sqlQuery = "SELECT so.Order_ID, so.Customer_ID, so.Order_Date, so.Cust_Feedback, so.Cust_Driver_Rating, " +
                      "so.Pick_Up_Apartment_Number, so.Pick_Up_Street_Name, so.Pick_Up_District, so.Pick_Up_Ward, so.Pick_Up_City, " +
                      "so.Final_Receiver_Name, so.Final_Receiver_Phone, so.Final_Apartment_Number, so.Final_Street_Name, so.Final_District, " +
                      "so.Final_Ward, so.Final_City, os.Status, os.Process, sod.Driver_ID " +
                      "FROM Shipment_Order so " +
                      "JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                      "JOIN Order_Status os ON so.Order_ID = os.Order_ID " +
                      "WHERE so.Order_ID = ?";

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, orderId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                Customer customer = getCustomerbyCustID(String.valueOf(customerId));
                String driverId = rs.getString("Driver_ID");
                Driver driver = driverImplement.getDriver(driverId, null, null).get(0);
                

//                Timestamp timestamp = rs.getTimestamp("Order_Date");
//                String orderDate = timestamp != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;

                
                order.setCustomer(customer);
                order.setOrder_id(rs.getInt("Order_ID"));
//                order.setOrder_date(orderDate);
                order.setCust_driver_rating(rs.getInt("Cust_Driver_Rating"));
                order.setCust_feedback(rs.getString("Cust_Feedback"));
                order.setPick_up_apartment_number(rs.getString("Pick_Up_Apartment_Number"));
                order.setPick_up_street_name(rs.getString("Pick_Up_Street_Name"));
                order.setPick_up_district(rs.getString("Pick_Up_District"));
                order.setPick_up_ward(rs.getString("Pick_Up_Ward"));
                order.setPick_up_city(rs.getString("Pick_Up_City"));
                order.setFinal_receiver_name(rs.getString("Final_Receiver_Name"));
                order.setFinal_receiver_phone(rs.getString("Final_Receiver_Phone"));
                order.setFinal_apartment_number(rs.getString("Final_Apartment_Number"));
                order.setFinal_street_name(rs.getString("Final_Street_Name"));
                order.setFinal_district(rs.getString("Final_District"));
                order.setFinal_ward(rs.getString("Final_Ward"));
                order.setFinal_city(rs.getString("Final_City"));

                
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }
    return order;
}
//------------------------------------------------------------------------//
//-----------------------------------------------------------------------//
      
      
       public Package_Detail getPackageDetailsByOrderId(String orderId) {
       
        String sqlQuery = "SELECT * FROM Package_Details WHERE Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    
                    Shipment_Order shipment = getShipmentOrdersByOrderId(orderId);
                    
                    
                    Timestamp timestamp = rs.getTimestamp("Requested_Delivery_Date");
                    String requested_Delivery_Date = timestamp != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
                    
                    
                    Timestamp timestamp1 = rs.getTimestamp("Requested_Receiving_Date");
                    String requested_Receiving_Date = timestamp1 != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
                    Package_Detail packageDetail = new Package_Detail();

                    packageDetail.setShipment_order(shipment);
                    packageDetail.setPackage_type(getPackageTypeById(String.valueOf(rs.getInt("Package_Type_ID"))));
                    packageDetail.setPackage_size(getPackageSizesById(String.valueOf(rs.getInt("Package_Size_ID"))));
                    packageDetail.setDelivery_type(getDeliveryTypeById(String.valueOf(rs.getInt("Delivery_Type_ID"))));
                    packageDetail.setRequested_delivery_date(requested_Delivery_Date);
                    packageDetail.setPackage_weight(rs.getFloat("Package_Weight"));
                    packageDetail.setPackage_value(rs.getDouble("Package_Value"));
                    packageDetail.setPackage_img(rs.getString("Package_Image"));
                    packageDetail.setPackage_note(rs.getString("Package_Note"));
                    packageDetail.setRequested_receiving_date(requested_Receiving_Date);
                    packageDetail.setPackage_receiving_note(rs.getString("Package_Receiving_Note"));
                    
                    return packageDetail;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
       //------------------------------------------------------------------------//
//-----------------------------------------------------------------------//
      
      
       public ArrayList<Package_Detail> getPackageDetailsByDriverId(String driverId) {
           
        ArrayList<Package_Detail> packageDetailsList = new ArrayList<>();
        
        String sqlQuery = "SELECT pd.* " +
                      "FROM Package_Details pd " +
                      "JOIN Shipment_Order so ON pd.Order_ID = so.Order_ID " +
                      "JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                      "JOIN Order_Status os ON so.Order_ID = os.Order_ID " +
                      "WHERE sod.Driver_ID = ? " +
                      "AND os.Status IN ('Picking up', 'In transit', 'Delivering') " +
                      "AND os.Process = 'Ongoing'";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                     Package_Detail packageDetail = new Package_Detail();
                    Shipment_Order shipment = getShipmentOrdersByOrderId(String.valueOf(rs.getInt("Order_ID")));
                    
                    
                    Timestamp timestamp = rs.getTimestamp("Requested_Delivery_Date");
                    String requested_Delivery_Date = timestamp != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
                    
                    
                    Timestamp timestamp1 = rs.getTimestamp("Requested_Receiving_Date");
                    String requested_Receiving_Date = timestamp1 != null ? timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null;
                    

                    packageDetail.setShipment_order(shipment);
                    packageDetail.setPackage_type(getPackageTypeById(String.valueOf(rs.getInt("Package_Type_ID"))));
                    packageDetail.setPackage_size(getPackageSizesById(String.valueOf(rs.getInt("Package_Size_ID"))));
                    packageDetail.setDelivery_type(getDeliveryTypeById(String.valueOf(rs.getInt("Delivery_Type_ID"))));
                    packageDetail.setRequested_delivery_date(requested_Delivery_Date);
                    packageDetail.setPackage_weight(rs.getFloat("Package_Weight"));
                    packageDetail.setPackage_value(rs.getDouble("Package_Value"));
                    packageDetail.setPackage_img(rs.getString("Package_Image"));
                    packageDetail.setPackage_note(rs.getString("Package_Note"));
                    packageDetail.setRequested_receiving_date(requested_Receiving_Date);
                    packageDetail.setPackage_receiving_note(rs.getString("Package_Receiving_Note"));
                    
                    packageDetailsList.add(packageDetail);
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return packageDetailsList;
    }
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       
       public ArrayList<Package_Type> getPackageTypes() {
        ArrayList<Package_Type> packageTypesList = new ArrayList<>();
        String sqlQuery = "SELECT Package_Type_ID, Package_Type_Value, Package_Type_Price FROM Package_Type";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int packageTypeId = rs.getInt("Package_Type_ID");
                String packageTypeValue = rs.getString("Package_Type_Value");
                float packageTypePrice = rs.getFloat("Package_Type_Price");

                Package_Type packageType = new Package_Type(packageTypeId, packageTypeValue, packageTypePrice);
                packageTypesList.add(packageType);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return packageTypesList;
    }
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       public Package_Type getPackageTypeById(String id) {
    String sqlQuery = "SELECT Package_Type_ID, Package_Type_Value, Package_Type_Price FROM Package_Type WHERE Package_Type_ID = ?";

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int packageTypeId = rs.getInt("Package_Type_ID");
                String packageTypeValue = rs.getString("Package_Type_Value");
                float packageTypePrice = rs.getFloat("Package_Type_Price");

                return new Package_Type(packageTypeId, packageTypeValue, packageTypePrice);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null; // Return null if no matching package type is found
}
    //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
       
       public Delivery_Type getDeliveryTypeById(String id) {
    String sqlQuery = "SELECT delivery_type_id, delivery_type_value FROM Delivery_Type WHERE delivery_type_id = ?";

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int deliveryTypeId = rs.getInt("delivery_type_id");
                String deliveryTypeValue = rs.getString("delivery_type_value");

                return new Delivery_Type(deliveryTypeId, deliveryTypeValue);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return null; 
}
       //------------------------------------------------------------------------//
       //-----------------------------------------------------------------------//
       
       public ArrayList<Package_Size> getPackageSizes() {
        ArrayList<Package_Size> packageSizesList = new ArrayList<>();
        String sqlQuery = "SELECT Package_Size_ID, Package_Size_Value, Package_Size_Price FROM Package_Size";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int packageSizeId = rs.getInt("Package_Size_ID");
                String packageSizeValue = rs.getString("Package_Size_Value");
                float packageSizePrice = rs.getFloat("Package_Size_Price");

                Package_Size packageSize = new Package_Size(packageSizeId, packageSizeValue, packageSizePrice);
                packageSizesList.add(packageSize);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return packageSizesList;
    }
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       public Package_Size getPackageSizesById(String id) {
        String sqlQuery = "SELECT Package_Size_ID, Package_Size_Value, Package_Size_Price FROM Package_Size WHERE Package_Size_ID = ?";
        
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int packageSizeId = rs.getInt("Package_Size_ID");
                    String packageSizeValue = rs.getString("Package_Size_Value");
                    float packageSizePrice = rs.getFloat("Package_Size_Price");
                    return new Package_Size(packageSizeId, packageSizeValue, packageSizePrice);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; 
    }

   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       public Customer getCustomerbyCustID(String customerID){
           Account acc = getAccountByAccID(customerID);
           int Id = Integer.parseInt(customerID);
           Customer customer = new Customer(acc,Id);
           return customer;
       }
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       
       public Account getAccountByAccID(String accountID) {
        Account acc = null;
        String sqlQuery = "SELECT Account_ID, Fname, Lname, Email, Phone, Gender, Password, CCCD_number, Avatar, Dob, Role_ID, Status FROM Account WHERE Account_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, accountID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("Account_ID");
                    String firstName = rs.getString("Fname");
                    String lastName = rs.getString("Lname");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("Phone");
                    char gender = rs.getString("Gender").charAt(0);
                    String password = rs.getString("Password");
                    String cccdNumber = rs.getString("CCCD_number");
                    String avatar = rs.getString("Avatar");
                    Date dob = rs.getDate("Dob");
                    int role = rs.getInt("Role_ID");
                    boolean status = rs.getBoolean("Status");

                    acc = new Account(accountId, firstName, lastName, email, password, phoneNumber, dob, cccdNumber, avatar, gender, role, status);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return acc;
    }
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       public Order_Status getOrder_StatusbyOrderID(String orderID) {
    Order_Status orderStatus = null;
      String sqlQuery = "SELECT os.Order_Status_ID, os.Order_ID, os.Location, os.Location_Type, os.Status, os.Start_Time, os.End_Time, os.Post_Office_ID, os.Process,os.Shipment_Driver_ID, " +
                          "po.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City " +
                          "FROM Order_Status os " +
                          "LEFT JOIN Post_Office po ON os.Post_Office_ID = po.Post_Office_ID " +
                          "WHERE os.Order_ID = ? " +
                          "AND (os.Process = 'Ongoing' OR (os.Status = 'Delivered' AND os.Process = 'Done'))";

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, orderID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int orderStatusID = rs.getInt("Order_Status_ID");
                int shipmentOrderID = rs.getInt("Order_ID");
                String location = rs.getString("Location");
                String locationType = rs.getString("Location_Type");
                String status = rs.getString("Status");
                Timestamp startTimeRaw = rs.getTimestamp("Start_Time");
                Timestamp endTimeRaw = rs.getTimestamp("End_Time");
                String process = rs.getString("Process");
                String Shipment_Driver_ID = String.valueOf(rs.getInt("Shipment_Driver_ID"));
                
                System.out.println(startTimeRaw);
                System.out.println(endTimeRaw);
                
                LocalDateTime startTime = startTimeRaw != null ? startTimeRaw.toLocalDateTime(): null;
                LocalDateTime endTime = endTimeRaw != null ? endTimeRaw.toLocalDateTime(): null;

                
                int postOfficeID = rs.getInt("Post_Office_ID");
                Shipment_Order_Driver shipment_Order_Driver = getShipment_Order_DriverById(Shipment_Driver_ID);
                Post_Office postOffice = getPostOfficeByID(postOfficeID);

                // Create the Shipment_Order object
                Shipment_Order shipmentOrder = getShipmentOrdersByOrderId(orderID);

                // Create the Order_Status object
                orderStatus = new Order_Status(orderStatusID, shipmentOrder, location, locationType, status,postOffice, startTime, endTime, process,shipment_Order_Driver);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return orderStatus;
}
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
          public Order_Status getOrder_StatusbyID(String ID) {
    Order_Status orderStatus = null;
      String sqlQuery = "SELECT os.Order_Status_ID, os.Order_ID, os.Location, os.Location_Type, os.Status, os.Start_Time, os.End_Time, os.Post_Office_ID, os.Process,os.Shipment_Driver_ID, " +
                          "po.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City " +
                          "FROM Order_Status os " +
                          "LEFT JOIN Post_Office po ON os.Post_Office_ID = po.Post_Office_ID " +
                          "WHERE os.Order_Status_ID = ? " ;

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, ID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int orderStatusID = rs.getInt("Order_Status_ID");
                int shipmentOrderID = rs.getInt("Order_ID");
                String location = rs.getString("Location");
                String locationType = rs.getString("Location_Type");
                String status = rs.getString("Status");
                Timestamp startTimeRaw = rs.getTimestamp("Start_Time");
                Timestamp endTimeRaw = rs.getTimestamp("End_Time");
                String process = rs.getString("Process");
                String Shipment_Driver_ID = String.valueOf(rs.getInt("Shipment_Driver_ID"));
                
                System.out.println(startTimeRaw);
                System.out.println(endTimeRaw);
                
                LocalDateTime startTime = startTimeRaw != null ? startTimeRaw.toLocalDateTime(): null;
                LocalDateTime endTime = endTimeRaw != null ? endTimeRaw.toLocalDateTime(): null;

                
                int postOfficeID = rs.getInt("Post_Office_ID");
                Shipment_Order_Driver shipment_Order_Driver = getShipment_Order_DriverById(Shipment_Driver_ID);
                Post_Office postOffice = getPostOfficeByID(postOfficeID);

                // Create the Shipment_Order object
                Shipment_Order shipmentOrder = getShipmentOrdersByOrderId(ID);

                // Create the Order_Status object
                orderStatus = new Order_Status(orderStatusID, shipmentOrder, location, locationType, status,postOffice, startTime, endTime, process,shipment_Order_Driver);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return orderStatus;
}
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
        public Payment_Method getPaymentMethodById(String id) {
        Payment_Method paymentMethod = null;
        String sqlQuery = "SELECT Payment_Method_ID, Payment_Method_Value FROM Payment_Method WHERE Payment_Method_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int paymentMethodId = rs.getInt("Payment_Method_ID");
                    String methodName = rs.getString("Payment_Method_Value");

                    
                    paymentMethod = new Payment_Method(paymentMethodId, methodName);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paymentMethod;
    }
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//  
        
       public Invoice getInvoiceByOrderId(String orderId) {
        Invoice invoice = null;
        String sqlQuery = "SELECT i.Service_Fee, i.Total_Amount, i.Payment_Method_ID, pm.Payment_Method_ID " +
                          "FROM Invoice i " +
                          "JOIN Payment_Method pm ON i.Payment_Method_ID = pm.Payment_Method_ID " +
                          "WHERE i.Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    float serviceFee = rs.getFloat("Service_Fee");
                    float totalAmount = rs.getFloat("Total_Amount");

                    int paymentMethodId = rs.getInt("Payment_Method_ID");
                    
                    Payment_Method paymentMethod = getPaymentMethodById(String.valueOf(paymentMethodId));

                    // Create the Shipment_Order object by calling a method to fetch it
                    Shipment_Order shipmentOrder = getShipmentOrdersByOrderId(orderId);

                    // Create the Invoice object
                    invoice = new Invoice(shipmentOrder,paymentMethod, serviceFee, totalAmount);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return invoice;
    }
       
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
       
        public Manager getRandomManagerIdByPostOfficeId(String post_id) {
        // SQL query to select Manager details based on Post_Office_ID
        String sqlQuery = "SELECT *"+
                          "FROM Manager  " +
                          "WHERE Post_Office_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the Post_Office_ID parameter
            ps.setString(1, post_id);

            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                List<Manager> managers = new ArrayList<>();
                
                // Retrieve Manager details associated with the given Post_Office_ID
                while (rs.next()) {
                    int managerId = rs.getInt("Manager_ID");
                    
                    Account account  = getAccountByAccID(String.valueOf(managerId));
                    Post_Office post_office = getPostOfficeByID(Integer.parseInt(post_id));
                    Manager manager = new Manager(managerId, post_office,account );
                    
                    managers.add(manager);
                }
                
                // If there are Manager objects found, return a random one
                if (!managers.isEmpty()) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(managers.size());
                    return managers.get(randomIndex);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving random Manager: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Manager is found or an error occurs
    }
        
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
        
        public Manager getManagerById(String id) {
        // SQL query to select Manager details based on Post_Office_ID
        String sqlQuery = "SELECT *"+
                          "FROM Manager  " +
                          "WHERE Manager_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the Post_Office_ID parameter
            ps.setString(1, id);

            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                
                
                // Retrieve Manager details associated with the given Post_Office_ID
                while (rs.next()) {
                    int managerId = rs.getInt("Manager_ID");
                    int post_id = rs.getInt("Post_Office_ID");
                    
                    Account account  = getAccountByAccID(String.valueOf(managerId));
                    Post_Office post_office = getPostOfficeByID(post_id);
                   
                    return new Manager(managerId, post_office, account);
                    
                }
                
                   
                
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving random Manager: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Manager is found or an error occurs
    }
   //------------------------------------------------------------------------//
   //-----------------------------------------------------------------------//
          public Staff getStaffById(String id) {
        // SQL query to select Manager details based on Post_Office_ID
        String sqlQuery = "SELECT *"+
                          "FROM Staff  " +
                          "WHERE Staff_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

           
            ps.setString(1, id);

           
            try (ResultSet rs = ps.executeQuery()) {
                
                
                // Retrieve Manager details associated with the given Post_Office_ID
                while (rs.next()) {
                    int staffId = rs.getInt("Staff_ID");
                    int post_id = rs.getInt("Post_Office_ID");
                    
                    Account account  = getAccountByAccID(String.valueOf(staffId));
                    Post_Office post_office = getPostOfficeByID(post_id);
                   
                    return new Staff(staffId, post_office, account);
                    
                }
                
                   
                
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving random Manager: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Manager is found or an error occurs
    }
          //------------------------------------------------------------------------//
          //-----------------------------------------------------------------------//
          
        public ArrayList<Ticket_Status> getTicketStatus() {
        ArrayList<Ticket_Status> ticketStatusList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Ticket_Status";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {
            
           

            while (rs.next()) {
                int ticketStatusId = rs.getInt("Ticket_Status_ID");
                String ticketStatusValue = rs.getString("Ticket_Status_Value");

                // Create Ticket_Status object and add to list
                Ticket_Status ticketStatus = new Ticket_Status(ticketStatusId, ticketStatusValue);
                ticketStatusList.add(ticketStatus);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Ticket Statuses: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return ticketStatusList;
    }
           
//------------------------------------------------------------------------//
//-----------------------------------------------------------------------//
    public Ticket_Status getTicketStatusByStatus(String status) {
        String sqlQuery = "SELECT * FROM Ticket_Status WHERE Ticket_Status_Value = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the parameter for the SQL query
            ps.setString(1, status);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int ticketStatusId = rs.getInt("Ticket_Status_ID");
                    String ticketStatusValue = rs.getString("Ticket_Status_Value");

                    // Create and return a Ticket_Status object
                    return new Ticket_Status(ticketStatusId, ticketStatusValue);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Ticket Status by status: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Ticket_Status is found or an error occurs
    }
    //------------------------------------------------------------------------//
    //------------------------------------------------------------------------//
        public Ticket_Status getTicketStatusById(String id) {
        String sqlQuery = "SELECT * FROM Ticket_Status WHERE Ticket_Status_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the parameter for the SQL query
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int ticketStatusId = rs.getInt("Ticket_Status_ID");
                    String ticketStatusValue = rs.getString("Ticket_Status_Value");

                   
                    return new Ticket_Status(ticketStatusId, ticketStatusValue);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Ticket Status by status: " + ex.getMessage());
           
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null; 
    }
    //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
 public boolean addImp_Ex_Ticket(Import_Export_Ticket ticket) {
        String sqlQuery = "INSERT INTO Import_Export_Ticket (Post_Office_ID, Creator_ID, Moderator_ID, Package_Details_ID, Ticket_Status_ID, Create_Date, Consental_Date, integrity, labeling, quantity, documentation, checkboxStatus, ticketType, Note) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set parameters for the SQL query
            ps.setInt(1, ticket.getPost_office().getPost_office_id());
            ps.setInt(2, ticket.getStaff().getStaff_id()); 
            ps.setInt(3, ticket.getManager().getManager_id()); // Assuming Moderator_ID is Manager_ID
            ps.setInt(4, ticket.getPackage_detail().getShipment_order().getOrder_id());
            ps.setInt(5, ticket.getTicket_status().getTicket_status_id());
            ps.setDate(6, new java.sql.Date(ticket.getCreate_date().getTime())); // Assuming Create_Date is java.util.Date
            ps.setDate(7, null); // Assuming Consental_Date is nullable, adjust as per your application logic
            ps.setString(8, ticket.getIntegrity());
            ps.setString(9, ticket.getLabeling());
            ps.setString(10, ticket.getQuantity());
            ps.setString(11, ticket.getDocumentation());
            String checkboxStatus = String.join(",", ticket.getCheckboxStatus());
            ps.setString(12, checkboxStatus);
            ps.setString(13, ticket.getTicketType());
            ps.setString(14, ticket.getNote());

            // Execute the insert query
            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException ex) {
            System.out.println("Error adding Import_Export_Ticket: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return false; // Return false if insertion fails
    }
    

//------------------------------------------------------------------------//
//-----------------------------------------------------------------------//
    
    
//------------------------------------------------------------------------//
//-----------------------------------------------------------------------//
    public Import_Export_Ticket getImp_Ex_TicketById(String id) {
        // SQL query to select Import_Export_Ticket details
        String sqlQuery = "SELECT * FROM Import_Export_Ticket WHERE Imp_Exp_Ticket_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the Imp_Exp_Ticket_ID parameter
            ps.setString(1, id);

            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Retrieve fields from the result set
                    int impExpTicketId = rs.getInt("Imp_Exp_Ticket_ID");
                    int postOfficeId = rs.getInt("Post_Office_ID");
                    int creatorId = rs.getInt("Creator_ID");
                    int moderatorId = rs.getInt("Moderator_ID");
                    int packageDetailsId = rs.getInt("Package_Details_ID");
                    int ticketStatusId = rs.getInt("Ticket_Status_ID");
                    Date createDate = rs.getDate("Create_Date");
                    Date censorialDate = rs.getDate("Consental_Date");
                    String integrity = rs.getString("integrity");
                    String labeling = rs.getString("labeling");
                    String quantity = rs.getString("quantity");
                    String documentation = rs.getString("documentation");
                    String checkboxStatusString = rs.getString("checkboxStatus");
                    String note = rs.getString("Note");
                    String ticketType = rs.getString("ticketType");

                    // Split the checkboxStatusString into an array of strings
                    String[] checkboxStatus = checkboxStatusString.split(",");

                    // Create corresponding objects for associated classes
                    
                    Post_Office postOffice = getPostOfficeByID(postOfficeId);

                    
                    Staff creator = getStaffById(String.valueOf(creatorId));

                    
                    Manager moderator = getManagerById(String.valueOf(moderatorId));

                    
                    Package_Detail packageDetail = getPackageDetailsByOrderId(String.valueOf(packageDetailsId));

                   
                    Ticket_Status ticketStatus = getTicketStatusById(String.valueOf(ticketStatusId));

                    
                    Import_Export_Ticket ticket = new Import_Export_Ticket();
                    ticket.setImport_export_ticket_id(impExpTicketId);
                    ticket.setPost_office(postOffice);
                    ticket.setStaff(creator);
                    ticket.setManager(moderator);
                    ticket.setPackage_detail(packageDetail);
                    ticket.setTicket_status(ticketStatus);
                    ticket.setCreate_date(createDate);
                    ticket.setCensorial_date(censorialDate);
                    ticket.setIntegrity(integrity);
                    ticket.setLabeling(labeling);
                    ticket.setQuantity(quantity);
                    ticket.setDocumentation(documentation);
                    ticket.setCheckboxStatus(checkboxStatus);
                    ticket.setNote(note);
                    ticket.setTicketType(ticketType);

                    return ticket;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Import_Export_Ticket: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Import_Export_Ticket is found or an error occurs
    }
        //------------------------------------------------------------------------//
        //-----------------------------------------------------------------------//
    
    public ArrayList<Import_Export_Ticket> getImp_Ex_TicketByStaffId(String id) {
        String sqlQuery = "SELECT * FROM Import_Export_Ticket WHERE Creator_ID = ?";
        ArrayList<Import_Export_Ticket> tickets = new ArrayList<>();

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int impExpTicketId = rs.getInt("Imp_Exp_Ticket_ID");
                    int postOfficeId = rs.getInt("Post_Office_ID");
                    int creatorId = rs.getInt("Creator_ID");
                    int moderatorId = rs.getInt("Moderator_ID");
                    int packageDetailsId = rs.getInt("Package_Details_ID");
                    int ticketStatusId = rs.getInt("Ticket_Status_ID");
                    Date createDate = rs.getDate("Create_Date");
                    Date censorialDate = rs.getDate("Consental_Date");
                    String integrity = rs.getString("integrity");
                    String labeling = rs.getString("labeling");
                    String quantity = rs.getString("quantity");
                    String documentation = rs.getString("documentation");
                    String checkboxStatusString = rs.getString("checkboxStatus");
                    String note = rs.getString("Note");
                    String ticketType = rs.getString("ticketType");

                    String[] checkboxStatus = checkboxStatusString.split(",");

                    Post_Office postOffice = getPostOfficeByID(postOfficeId);
                    Staff creator = getStaffById(String.valueOf(creatorId));
                    Manager moderator = getManagerById(String.valueOf(moderatorId));
                    Package_Detail packageDetail = getPackageDetailsByOrderId(String.valueOf(packageDetailsId));
                    Ticket_Status ticketStatus = getTicketStatusById(String.valueOf(ticketStatusId));

                    Import_Export_Ticket ticket = new Import_Export_Ticket();
                    ticket.setImport_export_ticket_id(impExpTicketId);
                    ticket.setPost_office(postOffice);
                    ticket.setStaff(creator);
                    ticket.setManager(moderator);
                    ticket.setPackage_detail(packageDetail);
                    ticket.setTicket_status(ticketStatus);
                    ticket.setCreate_date(createDate);
                    ticket.setCensorial_date(censorialDate);
                    ticket.setIntegrity(integrity);
                    ticket.setLabeling(labeling);
                    ticket.setQuantity(quantity);
                    ticket.setDocumentation(documentation);
                    ticket.setCheckboxStatus(checkboxStatus);
                    ticket.setNote(note);
                    ticket.setTicketType(ticketType);

                    tickets.add(ticket);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Import_Export_Tickets: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return tickets; // Return the list of Import_Export_Tickets
    }
        //------------------------------------------------------------------------//
        //-----------------------------------------------------------------------//
     public ArrayList<Import_Export_Ticket> getImp_Ex_TicketByManagerId(String id) {
        String sqlQuery = "SELECT * FROM Import_Export_Ticket WHERE Moderator_ID = ?";
        ArrayList<Import_Export_Ticket> tickets = new ArrayList<>();

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int impExpTicketId = rs.getInt("Imp_Exp_Ticket_ID");
                    int postOfficeId = rs.getInt("Post_Office_ID");
                    int creatorId = rs.getInt("Creator_ID");
                    int moderatorId = rs.getInt("Moderator_ID");
                    int packageDetailsId = rs.getInt("Package_Details_ID");
                    int ticketStatusId = rs.getInt("Ticket_Status_ID");
                    Date createDate = rs.getDate("Create_Date");
                    Date censorialDate = rs.getDate("Consental_Date");
                    String integrity = rs.getString("integrity");
                    String labeling = rs.getString("labeling");
                    String quantity = rs.getString("quantity");
                    String documentation = rs.getString("documentation");
                    String checkboxStatusString = rs.getString("checkboxStatus");
                    String note = rs.getString("Note");
                    String ticketType = rs.getString("ticketType");

                    String[] checkboxStatus = checkboxStatusString.split(",");

                    Post_Office postOffice = getPostOfficeByID(postOfficeId);
                    Staff creator = getStaffById(String.valueOf(creatorId));
                    Manager moderator = getManagerById(String.valueOf(moderatorId));
                    Package_Detail packageDetail = getPackageDetailsByOrderId(String.valueOf(packageDetailsId));
                    Ticket_Status ticketStatus = getTicketStatusById(String.valueOf(ticketStatusId));

                    Import_Export_Ticket ticket = new Import_Export_Ticket();
                    ticket.setImport_export_ticket_id(impExpTicketId);
                    ticket.setPost_office(postOffice);
                    ticket.setStaff(creator);
                    ticket.setManager(moderator);
                    ticket.setPackage_detail(packageDetail);
                    ticket.setTicket_status(ticketStatus);
                    ticket.setCreate_date(createDate);
                    ticket.setCensorial_date(censorialDate);
                    ticket.setIntegrity(integrity);
                    ticket.setLabeling(labeling);
                    ticket.setQuantity(quantity);
                    ticket.setDocumentation(documentation);
                    ticket.setCheckboxStatus(checkboxStatus);
                    ticket.setNote(note);
                    ticket.setTicketType(ticketType);

                    tickets.add(ticket);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving Import_Export_Tickets: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return tickets; // Return the list of Import_Export_Tickets
    }
        //------------------------------------------------------------------------//
        //-----------------------------------------------------------------------//
    public boolean isTicketValid(String orderId) {
        String sqlQuery =  "SELECT COUNT(*) AS count " +
                            "FROM Order_Status " +
                            "WHERE Order_ID = ? " +
                            "  AND ((Status = 'At warehouse' AND Process = 'Ongoing') " +
                            "       OR (Status = 'At warehouse-2' AND Process = 'Ongoing'))";
        boolean isValid = false;

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            
            ps.setString(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    isValid = count > 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isValid;
    }
    
    //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
public int countTicketByStatusId(String id, String postId) {
    String sqlQuery = "SELECT COUNT(*) AS count FROM Import_Export_Ticket WHERE Ticket_Status_ID = ? AND Post_Office_ID = ? AND Ticket_Status_ID = 3";
    int count = 0;
    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, id);
        ps.setString(2, postId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return count;
}

        
    
    //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
         public int countTicketByType(String type, String postId) {
    String sqlQuery = "SELECT COUNT(*) AS count FROM Import_Export_Ticket WHERE ticketType = ? AND Post_Office_ID = ? AND Ticket_Status_ID = 3";

    int count = 0;
    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        ps.setString(1, type);
        ps.setString(2, postId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return count;
}

        
    
    //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
         
public boolean updateTicketStatusId(String ticketId, String newStatusId) {
    String sqlQuery = "UPDATE Import_Export_Ticket SET Ticket_Status_ID = ? WHERE Imp_Exp_Ticket_ID = ?";
    Import_Export_Ticket ticket = getImp_Ex_TicketById(ticketId);
    int orderId = ticket.getPackage_detail().getShipment_order().getOrder_id();
    int orderStatusId = checkStageByProcess("Ongoing", String.valueOf(orderId));
    String ticketType = ticket.getTicketType();

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

        if (ticketType.equalsIgnoreCase("Export") && newStatusId.equalsIgnoreCase("3") ) {
            Order_Status orderS = getOrder_StatusbyID(String.valueOf(orderStatusId));
            if (orderS.getStatus().equalsIgnoreCase("At warehouse")) {
                // Update order_status with status = "At warehouse" to "Done" in the Order_Status table in the database
                String updateQuery1 = "UPDATE Order_Status SET Process = 'Done',End_Time = CURRENT_TIMESTAMP WHERE Order_ID = ? AND Status = 'At warehouse'";
                try (PreparedStatement psUpdate1 = conn.prepareStatement(updateQuery1)) {
                    psUpdate1.setInt(1, orderId);
                    psUpdate1.executeUpdate();
                }
                // Update order_status with status = "In Transit-2" to "Ongoing" in the Order_Status table in the database
                String updateQuery2 = "UPDATE Order_Status SET Process = 'Ongoing',Start_Time = CURRENT_TIMESTAMP WHERE Order_ID = ? AND Status = 'In Transit-2'";
                try (PreparedStatement psUpdate2 = conn.prepareStatement(updateQuery2)) {
                    psUpdate2.setInt(1, orderId);
                    psUpdate2.executeUpdate();
                    //Chuyển Driver
                    Post_Office post_Office_origin = driverImplement.getPostOfficeInTransit(orderId);
                    int post_Office_id = post_Office_origin.getPost_office_id();
                    driverImplement.changeDriverFromPostOffice(post_Office_id, orderId);
                    
                }
            } else if (orderS.getStatus().equalsIgnoreCase("At warehouse-2")) {
                // Update order_status with status = "At warehouse-2" to "Done" in the Order_Status table in the database
                String updateQuery1 = "UPDATE Order_Status SET Process = 'Done',End_Time = CURRENT_TIMESTAMP WHERE Order_ID = ? AND Status = 'At warehouse-2'";
                try (PreparedStatement psUpdate1 = conn.prepareStatement(updateQuery1)) {
                    psUpdate1.setInt(1, orderId);
                    psUpdate1.executeUpdate();
                }
                // Update order_status with status = "Delivering" to "Ongoing" in the Order_Status table in the database
                String updateQuery2 = "UPDATE Order_Status SET Process = 'Ongoing',Start_Time = CURRENT_TIMESTAMP WHERE Order_ID = ? AND Status = 'Delivering'";
                try ( PreparedStatement psUpdate2 = conn.prepareStatement(updateQuery2)) {
                    psUpdate2.setInt(1, orderId);
                    psUpdate2.executeUpdate();
                    //Chuyển Driver
                    Post_Office post_Office_origin = driverImplement.getPostOfficeAtwarehouse2(orderId);
                    int post_Office_id = post_Office_origin.getPost_office_id();
                    driverImplement.changeDriverFromFinalPostOffice(post_Office_id, orderId);
                }
            }
        }

        // Set the parameters for the main update query
        ps.setString(1, newStatusId);
        ps.setString(2, ticketId);

        // Execute the main update statement
        int rowsAffected = ps.executeUpdate();

        // Return true if at least one row was affected
        return rowsAffected > 0;

    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

 //------------------------------------------------------------------------//
    //------------------------------------------------------------------------//

    public int calculateImportExportTicketDifference(String postOfficeId) {
    String sqlQueryImport = "SELECT COUNT(*) AS count FROM Import_Export_Ticket " +
                            "WHERE ticketType = 'Import' AND Ticket_Status_ID = " +
                            "(SELECT Ticket_Status_ID FROM Ticket_Status WHERE Ticket_Status_Value = 'Approved') " +
                            "AND Post_Office_ID = ?";

    String sqlQueryExport = "SELECT COUNT(*) AS count FROM Import_Export_Ticket " +
                            "WHERE ticketType = 'Export' AND Ticket_Status_ID = " +
                            "(SELECT Ticket_Status_ID FROM Ticket_Status WHERE Ticket_Status_Value = 'Approved') " +
                            "AND Post_Office_ID = ?";

    int importCount = 0;
    int exportCount = 0;

    try (Connection conn = new DBcontext().getConnection();
         PreparedStatement psImport = conn.prepareStatement(sqlQueryImport);
         PreparedStatement psExport = conn.prepareStatement(sqlQueryExport)) {

        // Set the postOfficeId parameter for Import query
        psImport.setString(1, postOfficeId);
        // Execute the query for Import
        try (ResultSet rsImport = psImport.executeQuery()) {
            if (rsImport.next()) {
                importCount = rsImport.getInt("count");
            }
        }

        // Set the postOfficeId parameter for Export query
        psExport.setString(1, postOfficeId);
        // Execute the query for Export
        try (ResultSet rsExport = psExport.executeQuery()) {
            if (rsExport.next()) {
                exportCount = rsExport.getInt("count");
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
    }

    return importCount - exportCount;
}
    //------------------------------------------------------------------------//
    //------------------------------------------------------------------------//
public Shipment_Order_Driver getShipment_Order_DriverById(String id) {
    DriverImplement drvImp = new DriverImplement();
       ArrayList<Driver> driverList = new ArrayList<>();
        String sqlQuery = "SELECT *"+
                          "FROM Shipment_Order_Driver  " +
                          "WHERE Shipment_Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

           
            ps.setString(1, id);

           
            try (ResultSet rs = ps.executeQuery()) {
                
                
                // Retrieve Manager details associated with the given Post_Office_ID
                while (rs.next()) {
                    int Shipment_Driver_ID = rs.getInt("Shipment_Driver_ID");
                    int shipment_Order_id = rs.getInt("Shipment_Order_ID");
                    int driverId = rs.getInt("Driver_ID");
                    
                    driverList  = drvImp.getDriver(String.valueOf(driverId), null, null);
                    Driver driver = driverList.get(0);
                    Shipment_Order shipment_order = getShipmentOrdersByOrderId(String.valueOf(shipment_Order_id));
                   
                    return new Shipment_Order_Driver(Shipment_Driver_ID, shipment_order, driver);
                    
                }
                
                   
                
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving random Manager: " + ex.getMessage());
            // Log the exception or handle it according to your application's needs
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace(); // Print the stack trace for debugging
        }

        return null; // Return null if no Manager is found or an error occurs
    }


  //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//

public int checkStageByProcess(String process, String orderId) {
        String sqlQuery = "SELECT Order_Status_ID FROM Order_Status WHERE Order_ID = ? AND Process = ? AND (Status = 'At warehouse' OR Status = 'At warehouse-2')";
        
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the parameters for the query
            ps.setString(1, orderId);
            ps.setString(2, process);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Check the result
            if (rs.next()) {
                return rs.getInt("Order_Status_ID");
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; 
    }

    // Other methods and implementation details

  //------------------------------------------------------------------------//
    //-----------------------------------------------------------------------//   
    
      public static void main(String[] args) {
        GeneralImplement ger = new GeneralImplement();
//          System.out.println(ger.getPostOfficeByID(1));
//          System.out.println(ger.getShipmentOrdersByDriverId("4"));
//          System.out.println(ger.getPackageDetailsByOrderId("1"));
//          System.out.println(ger.getPackageSizes());
//          System.out.println(ger.getPackageTypes());
//          System.out.println(ger.getAccountByAccID("4"));
//          System.out.println(ger.getCustomerbyCustID("1"));
//          System.out.println(ger.getShipmentOrdersByOrderId("4"));
//       ArrayList<Package_Detail> packageDetailsList = ger.getPackageDetailsByDriverId("4");
//       for (Package_Detail packageDetail : packageDetailsList) {
//            System.out.println(packageDetail);
//        }
//          System.out.println(ger.getOrder_StatusbyOrderID("7"));
//            System.out.println(ger.getInvoiceByOrderId("1"));
//        System.out.println(ger.getStaffById("4"));

//ArrayList<Ticket_Status> ticket_Status = ger.getTicketStatus();
//for (Ticket_Status n : ticket_Status) {
//         System.out.println(n);
//        }
//            System.out.println(ger.getTicketStatusByStatus("Processing"));
//System.out.println(ger.countTicketByType("Import"));
System.out.println(ger.calculateImportExportTicketDifference("1"));

    }
}
