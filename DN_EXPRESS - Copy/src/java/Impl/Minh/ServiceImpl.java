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
import java.util.ArrayList;
import java.util.List;
import model.Package_Detail;
import model.Service_Property;
import model.Service_Type;

/**
 *
 * @author dangq
 */
public class ServiceImpl implements IRepository {
    
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
    
    public List<Service_Type> getAllServiceType() {
        List<Service_Type> serviceTypes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Service_Type";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("Service_Type_ID");
                String value = rs.getString("Service_Type_Value");
                Service_Type serviceType = new Service_Type();
                serviceType.setService_type_id(id);
                serviceType.setService_type_value(value);
                serviceTypes.add(serviceType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceTypes;
    }
    
    public Service_Type getServiceTypeById(int id) {
        String sqlQuery = "SELECT * FROM Service_Type WHERE Service_Type_ID = ?";
        Service_Type serviceType = null;
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int typeId = rs.getInt("Service_Type_ID");
                String typeName = rs.getString("Service_Type_Value");
                
                serviceType = new Service_Type();
                serviceType.setService_type_id(typeId);
                serviceType.setService_type_value(typeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return serviceType;
    }
    
    public List<Service_Property> getServicePropertiesByServiceType(int serviceTypeId) {
        List<Service_Property> serviceProperties = new ArrayList<>();
        String sqlQuery = "SELECT sp.Service_Property_ID, sp.Service_Property_Value, sp.Service_Property_Price, st.Service_Type_ID "
                + "FROM Service_Property sp "
                + "JOIN Service_Type st ON sp.Service_Type_ID = st.Service_Type_ID "
                + "WHERE st.Service_Type_ID = ?";
        
        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            
            ps.setInt(1, serviceTypeId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("Service_Property_ID");
                String value = rs.getString("Service_Property_Value");
                float price = rs.getFloat("Service_Property_Price");
                
                Service_Property serviceProperty = new Service_Property();
                serviceProperty.setService_property_id(id);
                serviceProperty.setService_property_value(value);
                serviceProperty.setService_property_price(price);
                
                Service_Type serviceType = getServiceTypeById(rs.getInt("Service_Type_ID"));
                serviceProperty.setService_type(serviceType);
                
                serviceProperties.add(serviceProperty);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return serviceProperties;
    }
    
    public void addPackageService(Service_Property serviceProperty, Package_Detail packageDetail) {
        String sqlQuery = "INSERT INTO Package_Service (Package_Details_ID, Service_Property_ID) VALUES (?, ?)";
        
        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set parameters
            ps.setInt(1, packageDetail.getShipment_order().getOrder_id());
            ps.setInt(2, serviceProperty.getService_property_id());

            // Execute insert statement
            int rowsAffected = ps.executeUpdate();
            System.out.println("PackageService Rows inserted: " + rowsAffected);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
