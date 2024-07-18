/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Minh;

import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Driver;
import model.Driver_type;
import model.Invoice;
import model.Order_Status;
import model.Package_Detail;
import model.Package_Type;
import model.Payment_Method;
import model.Post_Office;
import model.Shipment_Order;

/**
 *
 * @author dangq
 */
public class OrderImpl implements IRepository {

    static CustomerImplement customerImpl = new CustomerImplement();

    @Override
    public List display(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List find(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addShipmentOrder(Shipment_Order shipmentOrder) {
        String sqlQuery = "INSERT INTO Shipment_Order (Customer_ID, Created_Date,"
                + "Pick_Up_Apartment_Number, Pick_Up_Street_Name, Pick_Up_District, Pick_Up_Ward, Pick_Up_City, "
                + "Final_Receiver_Name, Final_Receiver_Phone, Final_Apartment_Number, Final_Street_Name, Final_District, Final_Ward, Final_City, In_Province) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, shipmentOrder.getCustomer().getAccount().getAccount_id());

            Timestamp Created_Date = Timestamp.valueOf(shipmentOrder.getCreated_date());
            ps.setTimestamp(2, Created_Date);

            ps.setString(3, shipmentOrder.getPick_up_apartment_number());
            ps.setString(4, shipmentOrder.getPick_up_street_name());
            ps.setString(5, shipmentOrder.getPick_up_district());
            ps.setString(6, shipmentOrder.getPick_up_ward());
            ps.setString(7, shipmentOrder.getPick_up_city());
            ps.setString(8, shipmentOrder.getFinal_receiver_name());
            ps.setString(9, shipmentOrder.getFinal_receiver_phone());
            ps.setString(10, shipmentOrder.getFinal_apartment_number());
            ps.setString(11, shipmentOrder.getFinal_street_name());
            ps.setString(12, shipmentOrder.getFinal_district());
            ps.setString(13, shipmentOrder.getFinal_ward());
            ps.setString(14, shipmentOrder.getFinal_city());
            ps.setBoolean(15, shipmentOrder.isIn_province());

            int rowsAffected = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    // Set the generated ID into the Shipment_Order object
                    shipmentOrder.setOrder_id(generatedId);
                } else {
                    throw new Exception("Inserting Shipment_Order failed, no rows affected.");
                }
            }
            System.out.println("ShipmentOrder Rows affected: " + rowsAffected);

            conn.commit();

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Shipment_Order getShipmentOrdersByOrderId(int orderId) {
        Shipment_Order order = new Shipment_Order();
        String sqlQuery = "SELECT * FROM Shipment_Order WHERE Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int customerId = rs.getInt("Customer_ID");
                    Customer customer = customerImpl.getCustomerbyCustID(String.valueOf(customerId));

//                    Timestamp orderDateTimeStamp = rs.getTimestamp("Order_Date");
//                    String orderDate = orderDateTimeStamp != null ? orderDateTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
//                    
                    Timestamp createdDateTimeStamp = rs.getTimestamp("Created_Date");
                    String createdDate = createdDateTimeStamp != null ? createdDateTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;

                    order.setCustomer(customer);
                    order.setOrder_id(rs.getInt("Order_ID"));
                    order.setCreated_date(createdDate);
//                    order.setOrder_date(orderDate);
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

    public List<Payment_Method> getAllPaymentMethods() {
        List<Payment_Method> paymentMethodList = new ArrayList<>();
        String sqlQuery = "SELECT Payment_Method_ID, Payment_Method_Value FROM Payment_Method";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Payment_Method_ID");
                String value = rs.getString("Payment_Method_Value");
                paymentMethodList.add(new Payment_Method(id, value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentMethodList;
    }

    public void addInvoice(Invoice invoice) {
        String sqlQuery = "INSERT INTO Invoice (Order_ID, Payment_Method_ID, Service_Fee, Total_Amount) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            // Set parameters
            ps.setInt(1, invoice.getShipment_order().getOrder_id());
            ps.setInt(2, invoice.getPayment_method().getPayment_method_id());
            ps.setDouble(3, invoice.getService_fee());
            ps.setDouble(4, invoice.getTotal_amount());

            // Execute insert statement
            int rowsAffected = ps.executeUpdate();
            System.out.println("Invoice Rows inserted: " + rowsAffected);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post_Office> getAllPostOffices() {
        List<Post_Office> postOffices = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Post_Office";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int postOfficeId = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");

                Post_Office postOffice = new Post_Office(postOfficeId, apartmentNumber, streetName, district, ward, city);
                postOffices.add(postOffice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postOffices;
    }

    public Post_Office getPostOfficeById(int postOfficeId) {
        Post_Office postOffice = null;
        String sqlQuery = "SELECT * FROM Post_Office WHERE Post_Office_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, postOfficeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");

                postOffice = new Post_Office(postOfficeId, apartmentNumber, streetName, district, ward, city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postOffice;
    }

    public List<Order_Status> getOrderStatusbyOrderId(int orderID) {
        List<Order_Status> statusList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Order_Status WHERE Order_ID = ? AND (Process = 'Ongoing' OR Process = 'Done')";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, orderID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderStatusID = rs.getInt("Order_Status_ID");
                    int shipmentOrderID = rs.getInt("Order_ID");
                    String location = rs.getString("Location");
                    String locationType = rs.getString("Location_Type");
                    String status = rs.getString("Status");
                    Timestamp startTimeRaw = rs.getTimestamp("Start_Time");
                    Timestamp endTimeRaw = rs.getTimestamp("End_Time");
                    String process = rs.getString("Process");

                    LocalDateTime startTime = startTimeRaw != null ? startTimeRaw.toLocalDateTime() : null;
                    LocalDateTime endTime = endTimeRaw != null ? endTimeRaw.toLocalDateTime() : null;

                    int postOfficeID = rs.getInt("Post_Office_ID");

                    Post_Office postOffice = getPostOfficeById(postOfficeID);

                    // Create the Shipment_Order object
                    Shipment_Order shipmentOrder = getShipmentOrdersByOrderId(orderID);

                    // Create the Order_Status object
                    Order_Status orderStatus = new Order_Status(orderStatusID, shipmentOrder, location, locationType, status, postOffice, startTime, endTime, process);
                    statusList.add(orderStatus);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return statusList;
    }

    public void cancelOrder(int orderId) {
        String sqlQuery = "UPDATE Order_Status SET process = 'Cancelled' WHERE Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
            System.out.println("Cancelled Order");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
