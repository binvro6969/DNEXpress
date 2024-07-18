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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Delivery_Type;
import model.Package_Detail;
import model.Package_Size;
import model.Package_Type;
import model.Shipment_Order;

/**
 *
 * @author dangq
 */
public class PackageImpl implements IRepository {

    static OrderImpl orderImpl = new OrderImpl();

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

    public List<Package_Type> getAllPackageTypes() {
        List<Package_Type> packageTypes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Package_Type";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("package_type_id");
                String value = rs.getString("package_type_value");
                float price = rs.getFloat("package_type_price");

                Package_Type packageType = new Package_Type();
                packageType.setPackage_type_id(id);
                packageType.setPackage_type_value(value);
                packageType.setPackage_type_price(price);
                
                packageTypes.add(packageType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageTypes;
    }

    public Package_Type getPackageTypeById(String id) {
        String sqlQuery = "SELECT Package_Type_ID, Package_Type_Value, Package_Type_Price FROM Package_Type WHERE Package_Type_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

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
            Logger.getLogger(PackageImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; // Return null if no matching package type is found
    }

    public List<Package_Size> getAllPackageSizes() {
        List<Package_Size> packageSizes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Package_Size";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("package_size_id");
                String value = rs.getString("package_size_value");
                float price = rs.getFloat("package_size_price");

                Package_Size packageSize = new Package_Size();
                packageSize.setPackage_size_id(id);
                packageSize.setPackage_size_value(value);
                packageSize.setPackage_size_price(price);

                packageSizes.add(packageSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageSizes;
    }

    public Package_Size getPackageSizesById(String id) {
        String sqlQuery = "SELECT Package_Size_ID, Package_Size_Value, Package_Size_Price FROM Package_Size WHERE Package_Size_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

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

    public List<Delivery_Type> getAllDeliveryTypes() {
        List<Delivery_Type> deliveryTypes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Delivery_Type";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("delivery_type_id");
                String value = rs.getString("delivery_type_value");
                float price = rs.getFloat("delivery_type_price");

                Delivery_Type deliveryType = new Delivery_Type();
                deliveryType.setDelivery_type_id(id);
                deliveryType.setDelivery_type_value(value);
                deliveryType.setDelivery_type_price(price);

                deliveryTypes.add(deliveryType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deliveryTypes;
    }

    public Delivery_Type getDeliveryTypeById(String id) {
        String sqlQuery = "SELECT delivery_type_id, delivery_type_value, delivery_type_price FROM Delivery_Type WHERE delivery_type_id = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int deliveryTypeId = rs.getInt("delivery_type_id");
                    String deliveryTypeValue = rs.getString("delivery_type_value");
                    float deliveryTypePrice = rs.getFloat("delivery_type_price");

                    return new Delivery_Type(deliveryTypeId, deliveryTypeValue, deliveryTypePrice);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void addPackageDetails(Package_Detail packageDetail) {
        String sqlQuery = "INSERT INTO Package_Details (Order_ID, Package_Type_ID, Package_Size_ID, Delivery_Type_ID, "
                + "Requested_Delivery_Date, Package_Weight, Package_Value, Package_Image, Package_Note, Requested_Receiving_Date, Package_Receiving_Note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setInt(1, packageDetail.getShipment_order().getOrder_id());
            ps.setInt(2, packageDetail.getPackage_type().getPackage_type_id());
            ps.setInt(3, packageDetail.getPackage_size().getPackage_size_id());
            ps.setInt(4, packageDetail.getDelivery_type().getDelivery_type_id());

            Timestamp Requested_Delivery_Date = Timestamp.valueOf(packageDetail.getRequested_delivery_date());
            ps.setTimestamp(5, Requested_Delivery_Date);

            ps.setFloat(6, packageDetail.getPackage_weight());
            ps.setDouble(7, packageDetail.getPackage_value());
            ps.setString(8, packageDetail.getPackage_img());
            ps.setString(9, packageDetail.getPackage_note());

            // Check if Requested_Receiving_Date is null
            if (packageDetail.getRequested_receiving_date() == null) {
                ps.setNull(10, java.sql.Types.TIMESTAMP);
            } else {
                Timestamp requestedReceivingDate = Timestamp.valueOf(packageDetail.getRequested_receiving_date());
                ps.setTimestamp(10, requestedReceivingDate);
            }

            // Check if Package_Receiving_Note is null
            if (packageDetail.getPackage_receiving_note() == null) {
                ps.setNull(11, java.sql.Types.NVARCHAR);
            } else {
                ps.setString(11, packageDetail.getPackage_receiving_note());
            }

            int rowsAffected = ps.executeUpdate();

            System.out.println("PackageDetails Rows affected: " + rowsAffected);

            conn.commit(); // Explicitly commit the transaction

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Package_Detail getPackageDetailsByOrderId(int orderId) {

        String sqlQuery = "SELECT * FROM Package_Details WHERE Order_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Shipment_Order shipment = orderImpl.getShipmentOrdersByOrderId(orderId);

                    String requestedDeliveryDate = rs.getString("Requested_Delivery_Date");
                    String requestedReceivingDate = rs.getString("Requested_Receiving_Date");

                    Package_Detail packageDetail = new Package_Detail();

                    packageDetail.setShipment_order(shipment);
                    packageDetail.setPackage_type(getPackageTypeById(String.valueOf(rs.getInt("Package_Type_ID"))));
                    packageDetail.setPackage_size(getPackageSizesById(String.valueOf(rs.getInt("Package_Size_ID"))));
                    packageDetail.setDelivery_type(getDeliveryTypeById(String.valueOf(rs.getInt("Delivery_Type_ID"))));
                    packageDetail.setRequested_delivery_date(requestedDeliveryDate);
                    packageDetail.setPackage_weight(rs.getFloat("Package_Weight"));
                    packageDetail.setPackage_value(rs.getDouble("Package_Value"));
                    packageDetail.setPackage_img(rs.getString("Package_Image"));
                    packageDetail.setPackage_note(rs.getString("Package_Note"));
                    packageDetail.setRequested_receiving_date(requestedReceivingDate);
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

}
