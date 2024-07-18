/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Dinh;

import Impl.Minh.OrderImpl;
import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Invoice;
import model.Payment_Method;
import model.Shipment_Order;

/**
 *
 * @author haian
 */
public class CustomerImplement implements IRepository<Customer> {

    //khai bao CustomerImplement và sử dụng có thể thêm các method khác không chỉnh sửa IRepository<>
    @Override
    public List<Customer> display(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Customer object) {

    }

    @Override
    public void edit(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Customer> find(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static OrderImpl orderImpl = new OrderImpl();

    public ArrayList<Invoice> getOrderHistory(int id) {
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT "
                + "    SO.Order_Date, "
                + "    SO.Created_Date, "
                + "    SO.Order_ID, "
                + "    SO.Cust_Driver_Rating,"
                + "    SO.Final_Receiver_Name, "
                + "    SO.Final_Receiver_Phone, "
                + "    SO.Final_Apartment_Number, "
                + "    SO.Final_Street_Name, "
                + "    SO.Final_District, "
                + "    SO.Final_Ward, "
                + "    SO.Final_City, "
                + "    I.Payment_Method_ID, "
                + "    I.Service_Fee, "
                + "    I.Total_Amount "
                + "FROM "
                + "    Shipment_Order AS SO "
                + "JOIN "
                + "    Invoice AS I "
                + "ON "
                + "    SO.Order_ID = I.Order_ID "
                + "WHERE "
                + "    SO.Customer_ID = ?;";

        try (Connection con = new DBcontext().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("Order_ID");
//                String orderDate = rs.getString("Order_Date");
                String finalReceiverName = rs.getString("Final_Receiver_Name");
                String finalReceiverPhone = rs.getString("Final_Receiver_Phone");
                String finalApartmentNumber = rs.getString("Final_Apartment_Number");
                String finalStreetName = rs.getString("Final_Street_Name");
                String finalDistrict = rs.getString("Final_District");
                String finalWard = rs.getString("Final_Ward");
                String finalCity = rs.getString("Final_City");
                int paymentMethodId = rs.getInt("Payment_Method_ID");
                float serviceFee = rs.getFloat("Service_Fee");
                float totalAmount = rs.getFloat("Total_Amount");

                int cusRating = rs.getInt("Cust_Driver_Rating");
                if (rs.wasNull()) {
                    cusRating = 0;
                }

                Timestamp orderDateTimeStamp = rs.getTimestamp("Order_Date");
                String orderDate = orderDateTimeStamp != null ? orderDateTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;

                Timestamp createdDateTimeStamp = rs.getTimestamp("Created_Date");
                String createdDate = createdDateTimeStamp != null ? createdDateTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;

                Shipment_Order so = new Shipment_Order();
                so.setOrder_id(orderId);
                so.setOrder_date(orderDate);
                so.setCreated_date(createdDate);
                so.setFinal_receiver_name(finalReceiverName);
                so.setFinal_receiver_phone(finalReceiverPhone);
                so.setFinal_apartment_number(finalApartmentNumber);
                so.setFinal_street_name(finalStreetName);
                so.setFinal_district(finalDistrict);
                so.setFinal_ward(finalWard);
                so.setFinal_city(finalCity);
                so.setCust_driver_rating(cusRating);

                Payment_Method paymentMethod = new Payment_Method();
                List<Payment_Method> paymentOptions = orderImpl.getAllPaymentMethods();
                for (Payment_Method pm : paymentOptions) {
                    if (pm.getPayment_method_id() == paymentMethodId) {
                        paymentMethod = pm;
                    }
                }

                Invoice inv = new Invoice(so, paymentMethod, serviceFee, totalAmount);
                list.add(inv);
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Account viewCustomerinfo(int id) {
        Account info = new Account();
        String sql = "SELECT [Lname], [Fname], [Email], [Phone], [Gender], [CCCD_number], [Dob], [Avatar] "
                + "FROM [dbo].[Account] WHERE [Account_ID] = ?";
        try {
            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String LName = rs.getString(1);
                String FName = rs.getString(2);
                String Email = rs.getString(3);
                String PhoneNum = rs.getString(4);
                String genderCheck = rs.getString(5);
                char Gender = 'M';
                if (genderCheck != null) {
                    Gender = genderCheck.charAt(0);
                }
                String CCCD = rs.getString(6);
                Date Dob = rs.getDate(7);
                String Avatar = rs.getString(8);

                info.setFirstName(FName);
                info.setLastName(LName);
                info.setEmail(Email);
                info.setPhone_numb(PhoneNum);
                info.setCccd_numb(CCCD);
                info.setGender(Gender);
                
                info.setDob(Dob);
                info.setAvatar(Avatar);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(CustomerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }

    public boolean updateInfo(Account account) {
        boolean test = false;
        String sql = "UPDATE [dbo].[Account] SET [Lname] = ?, [Fname] = ?, [Email] = ?,"
                + "[Phone] = ?, [Gender] = ?, [CCCD_number] = ?, [Dob] = ? "
                + "WHERE [Account_ID] = ?";
        char gender = account.getGender().charAt(0);
        try {
            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getLastName());
            ps.setString(2, account.getFirstName());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhone_numb());
            ps.setString(5, String.valueOf(gender));
            ps.setString(6, account.getCccd_numb());
            ps.setDate(7, account.getDob_Database());
            ps.setInt(8, account.getAccount_id());
            ps.executeUpdate();
            if (ps.executeUpdate() == 1) {
                return test = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

    public boolean ratingDriver(Shipment_Order smo, int id) {
        boolean test = false;
        try {

            String sql = "Update Shipment_Order SET Cust_Feedback=?,Cust_Driver_Rating=? WHERE Order_ID = ? ";

            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, smo.getCust_feedback());
            ps.setFloat(2, smo.getCust_driver_rating());
            ps.setInt(3, id);
            ps.executeUpdate();
            if (ps.executeUpdate() == 1) {
                return test = true;
            }

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return test;
    }

}
