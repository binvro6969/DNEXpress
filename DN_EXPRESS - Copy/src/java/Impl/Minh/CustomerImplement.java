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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Monthly_Statistic;
import model.Shipment_Order;

/**
 *
 * @author haian
 */
public class CustomerImplement implements IRepository<Customer> {

    static GeneralImplement generalImpl = new GeneralImplement();

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

    public static int getTotalOrders(int cusId) {
        String sqlQuery = "SELECT COUNT(*) AS totalOrders FROM Shipment_Order WHERE Customer_ID = ?";

        int totalOrders = 0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, cusId);

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

    public static int getDeliveredOrders(int cusId) {
        String sqlQuery = "SELECT COUNT(*) AS deliveredOrders "
                + "FROM Shipment_Order so "
                + "INNER JOIN Order_Status os ON so.Order_ID = os.Order_ID "
                + "WHERE so.Customer_ID = ? "
                + "AND (os.Status = 'Delivered' AND os.Process = 'Done')";

        int deliveredOrders = 0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, cusId);

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

    public static double getCustomerRating(int cusId) {
        String sqlQuery = "SELECT ROUND(AVG(Cust_Driver_rating), 2) AS AvgRating \n"
                + "FROM Shipment_Order \n"
                + "WHERE Customer_ID = ?;";
        double rating = 0.0;

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, cusId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rating = rs.getDouble("AvgRating");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rating;
    }

    public List<Monthly_Statistic> getMonthlyStatistics(int cusId, int year) {
        String sqlQuery = "SELECT "
                + "    YEAR(Order_Date) AS Order_Year, "
                + "    MONTH(Order_Date) AS Order_Month, "
                + "    COUNT(*) AS Order_Count, "
                + "    AVG(Cust_Driver_rating) AS Avg_Rating, "
                + "    COALESCE(SUM(total_amount), 0) AS Total_Amount "
                + // Sum of income using COALESCE to handle nulls
                "FROM Shipment_Order "
                + "LEFT JOIN Invoice ON Shipment_Order.order_id = Invoice.order_id "
                + "WHERE YEAR(Order_Date) = ? AND Customer_ID = ?"
                + "GROUP BY YEAR(Order_Date), MONTH(Order_Date) "
                + "ORDER BY YEAR(Order_Date), MONTH(Order_Date)";

        List<Monthly_Statistic> monthlyStats = new ArrayList<>();

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, year);
            ps.setInt(2, cusId);

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

    public List<Integer> getDistinctOrderYears(int cusId) {
        List<Integer> years = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT YEAR(Order_Date) AS OrderYear FROM Shipment_Order WHERE Customer_ID = ?";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, cusId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                years.add(rs.getInt("OrderYear"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return years;
    }

    public double getCustomerExpenses(int cusId) {
        double totalAmount = 0.0;
        String sql = "SELECT SUM(total_amount) AS totalAmount "
                + "FROM Invoice "
                + "WHERE order_id IN (SELECT order_id FROM Shipment_Order WHERE Customer_ID = ?)";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cusId);
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

    public Customer getCustomerbyCustID(String customerID) {
        Account acc = generalImpl.getAccountByAccID(customerID);
        int Id = Integer.parseInt(customerID);
        Customer customer = new Customer(acc, Id);
        return customer;
    }

    public String[] getFirstAndLastName(String fullName) {
        // Trim the full name to remove any leading or trailing whitespace
        fullName = fullName.trim();

        // Split the full name by spaces
        String[] nameParts = fullName.split("\\s+");

        // Handle cases where there might be no name or a single name part
        if (nameParts.length == 0) {
            return new String[]{"", ""};
        } else if (nameParts.length == 1) {
            return new String[]{nameParts[0], ""}; // Only first name available
        } else {
            // Combine all parts except the last one into the first name
            StringBuilder firstName = new StringBuilder();
            for (int i = 0; i < nameParts.length - 1; i++) {
                if (i > 0) {
                    firstName.append(" ");
                }
                firstName.append(nameParts[i]);
            }

            // Last part is the last name
            String lastName = nameParts[nameParts.length - 1];

            return new String[]{firstName.toString(), lastName};
        }
    }

}
