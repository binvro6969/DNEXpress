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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Driver;
import model.Driver_type;
import model.Monthly_Statistic;
import model.Post_Office;

/**
 *
 * @author haian
 */
public class DriverImplement implements IRepository<Driver> {
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

    @Override
    public List<Driver> find(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static int getTotalOrders(int driverId) {
        String sqlQuery = "SELECT COUNT(*) AS totalOrders\n"
                + "FROM Shipment_Order_Driver sod\n"
                + "JOIN Shipment_Order so ON sod.Shipment_Order_ID = so.Order_ID\n"
                + "WHERE sod.Driver_ID = ?";

        int totalOrders = 0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, driverId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("totalOrders");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalOrders;
    }

    public static int getDeliveredOrders(int driverId) {
        String sqlQuery = "SELECT COUNT(*) AS deliveredOrders\n"
                + "FROM Shipment_Order so\n"
                + "INNER JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID\n"
                + "INNER JOIN Order_Status os ON so.Order_ID = os.Order_ID\n"
                + "WHERE sod.Driver_ID = ?\n"
                + "AND (os.Status = 'Delivered' AND os.Process = 'Done')";

        int deliveredOrders = 0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, driverId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                deliveredOrders = rs.getInt("deliveredOrders");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deliveredOrders;
    }

    public static double getDriverRating(int driverId) {
        String sqlQuery = "SELECT Rating FROM Driver WHERE Driver_ID = ?";
        double rating = 0.0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, driverId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rating = rs.getDouble("Rating");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rating;
    }

    public List<Monthly_Statistic> getMonthlyStatistics(int driverId, int year) {
        String sqlQuery = "SELECT\n"
                + "    YEAR(so.Order_Date) AS Order_Year,\n"
                + "    MONTH(so.Order_Date) AS Order_Month,\n"
                + "    COUNT(*) AS Order_Count,\n"
                + "    AVG(so.Cust_Driver_Rating) AS Avg_Rating,\n"
                + "    COALESCE(SUM(i.total_amount), 0) AS Total_Amount\n"
                + "FROM Shipment_Order so\n"
                + "LEFT JOIN Invoice i ON so.Order_ID = i.Order_ID\n"
                + "INNER JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID\n"
                + "WHERE YEAR(so.Order_Date) = ? AND sod.Driver_ID = ?\n"
                + "GROUP BY YEAR(so.Order_Date), MONTH(so.Order_Date)\n"
                + "ORDER BY YEAR(so.Order_Date), MONTH(so.Order_Date);";

        List<Monthly_Statistic> monthlyStats = new ArrayList<>();

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, year);
            ps.setInt(2, driverId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderYear = rs.getInt("Order_Year");
                    int orderMonth = rs.getInt("Order_Month");
                    int orderCount = rs.getInt("Order_Count");
                    double avgRating = rs.getDouble("Avg_Rating");
                    double totalAmount = rs.getDouble("Total_Amount");

                    Monthly_Statistic stats = new Monthly_Statistic(orderYear, orderMonth, orderCount, avgRating, totalAmount);
                    monthlyStats.add(stats);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthlyStats;
    }

    public List<Integer> getDistinctOrderYears(int driverId) {
        List<Integer> years = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT YEAR(so.Order_Date) AS OrderYear\n"
                + "FROM Shipment_Order so\n"
                + "INNER JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID\n"
                + "WHERE sod.Driver_ID = ?\n"
                + "ORDER BY OrderYear;";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, driverId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                years.add(rs.getInt("OrderYear"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return years;
    }

    public double getDriverIncome(int driverId) {
        double totalAmount = 0.0;
        String sql = "SELECT SUM(total_amount) AS totalAmount\n"
                + "FROM Invoice\n"
                + "WHERE order_id IN (\n"
                + "    SELECT so.Order_ID\n"
                + "    FROM Shipment_Order so\n"
                + "    INNER JOIN Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID\n"
                + "    WHERE sod.Driver_ID = ?\n"
                + ");";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalAmount = rs.getDouble("totalAmount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalAmount;
    }

    
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
                Driver driver = new Driver(account, license_numb, driverType, status, latitude, longitude, postOffice);
                drivers.add(driver);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return drivers;
    }
    
    
}
