/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Duong;

import Impl.An.*;
import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Manager;
import model.Service_Property;
import model.Service_Type;


/**
 *
 * @author haian
 */
public class ManagerImplement implements IRepository<Manager>{
//khai bao CustomerImplement và sử dụng có thể thêm các method khác không chỉnh sửa IRepository<>
    @Override
    public List<Manager> display(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Manager> find(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Manager object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    //MANAGE SERVICE//
    public List<Service_Type> getAllService() {
                    List<Service_Type> list = new ArrayList<>();
                    String query = "SELECT * FROM Service_Type";
                    try {
                        Connection conn = new DBcontext().getConnection();
                        PreparedStatement ps = conn.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            int service_id = rs.getInt("Service_Type_ID");
                            
                            String service_type = rs.getString("Service_Type_Value");
                            
                            Service_Type ST = new Service_Type(service_id, service_type);
                            list.add(ST);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return list;
}

public Service_Type getServiceTypeById(int serviceTypeId) {
    Service_Type  serviceType = null;
    String query = "SELECT * FROM Service_Type WHERE Service_Type_ID = ?";

     try {
        Connection conn = new DBcontext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        
        ps.setInt(1, serviceTypeId);
        
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            serviceType = new Service_Type();
            serviceType.setService_type_id(rs.getInt("Service_Type_ID"));
            serviceType.setService_type_value(rs.getString("Service_Type_Value"));
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (Exception ex) { 
            Logger.getLogger(ManagerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    return serviceType;
}

public List<Service_Property> getService_PropertyByID(int Service_TypeID) {
      List<Service_Property> list = new ArrayList<>();
    
                    String query = "SELECT \n" +
                                    "    SP.Service_Property_ID, \n" +
                                    "    SP.Service_Property_Value, \n" +
                                    "    SP.Service_Property_Price, \n" +
                                    "    SP.Service_Type_ID\n" +
                                    "FROM \n" +
                                    "    Service_Property SP\n" +
                                    "WHERE \n" +
                                    "    SP.Service_Type_ID = ?;";

                    try {
                        Connection conn = new DBcontext().getConnection();
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setInt(1, Service_TypeID);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            int service_id = rs.getInt("Service_Type_ID");

                            int service_propertyID = rs.getInt("Service_Property_ID");
                            
                            String service_propertyValue = rs.getString("Service_Property_Value");
                            
                            float service_propertyPrice = rs.getFloat("Service_Property_Price");
                            
                            Service_Type ST = new Service_Type();
                            ST.setService_type_id(service_id);
                            
                            Service_Property SP = new Service_Property(ST, service_propertyID,  service_propertyValue, service_propertyPrice);
                            list.add(SP);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return list;
}

 public void updateServiceType(Service_Type serviceType, Service_Property serviceProperty)  {
        String query = "UPDATE Service_Type SET Service_Type_Value = ? WHERE Service_Type_ID = ?";
        
        int ser_type_id = serviceType.getService_type_id();
        String ser_type_value = serviceType.getService_type_value();
        
       try {
        Connection conn = new DBcontext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, ser_type_value);
        ps.setInt(2, ser_type_id);
        
        ps.executeUpdate();
        
        String query1 = "UPDATE Service_Property SET Service_Property_Value = ?, Service_Property_Price = ? WHERE Service_Property_ID = ?";
          int pro_ID = serviceProperty.getService_property_id();
       String pro_value = serviceProperty.getService_property_value();
       
       float pro_price = serviceProperty.getService_property_price();
       
        PreparedStatement ps1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);

        ps1.setString(1, pro_value);
        ps1.setFloat(2, pro_price);
        ps1.setInt(3, pro_ID);
        
        ps1.executeUpdate();
        
        conn.close();
        

    }   catch (Exception ex) { 
            Logger.getLogger(ManagerImplement.class.getName()).log(Level.SEVERE, null, ex);
        } 
 }
  
 
public void addServiceTypeWithProperties(Service_Type serviceType, List<Service_Property> serviceProperties) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            conn = new DBcontext().getConnection();
            conn.setAutoCommit(false); 

            // Thêm Service_Type
            String query1 = "INSERT INTO Service_Type (Service_Type_Value) VALUES (?)";
            ps1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, serviceType.getService_type_value());
            ps1.executeUpdate();
            rs = ps1.getGeneratedKeys();
            if (rs.next()) {
                int serviceTypeId = rs.getInt(1);
                serviceType.setService_type_id(serviceTypeId);

                // Thêm Service_Property
                String query2 = "INSERT INTO Service_Property (Service_Type_ID, Service_Property_Value, Service_Property_Price) VALUES (?, ?, ?)";
                ps2 = conn.prepareStatement(query2);
                for (Service_Property property : serviceProperties) {
                    ps2.setInt(1, serviceTypeId);
                    ps2.setString(2, property.getService_property_value());
                    ps2.setFloat(3, property.getService_property_price());
                    ps2.addBatch();
                }
                ps2.executeBatch();
            }

            conn.commit(); // Kết thúc transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerImplement.class.getName()).log(Level.SEVERE, null, ex);
        } 
     finally {
            try {
                if (rs != null) rs.close();
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

public boolean deleteService(int serviceTypeID) {
    String query = "DELETE FROM Service_Property WHERE Service_Type_ID = ?";

    String query1 = "DELETE FROM Service_Type WHERE Service_Type_ID = ?";
    try {
        Connection conn = new DBcontext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, serviceTypeID);
        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Error");
        e.printStackTrace();
    }

    try {
        Connection conn = new DBcontext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query1);

        ps.setInt(1, serviceTypeID);
        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Error");
        e.printStackTrace();
    }
    return false;
}

  public List<Service_Type> searchServices(String searchCriteria) {
        List<Service_Type> serviceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new DBcontext().getConnection();
            String query = "SELECT * FROM Service_Type WHERE Service_Type_ID = ? OR Service_Type_Value LIKE ?";
            ps = conn.prepareStatement(query);
            // Assuming the searchCriteria can be either ID or Service_Type value
            int id = -1;
            try {
                id = Integer.parseInt(searchCriteria);
            } catch (NumberFormatException e) {
                // Ignore if it's not an integer
            }
            ps.setInt(1, id);
            ps.setString(2, "%" + searchCriteria + "%"); // Partial match for Service_Type_Value
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Service_Type service = new Service_Type();
                service.setService_type_id(rs.getInt("Service_Type_ID"));
                service.setService_type_value(rs.getString("Service_Type_Value"));
                // Populate other attributes as needed
                serviceList.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly
        } finally {
            // Close resources in finally block
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return serviceList;
    }

      public boolean isServiceNameExists(String serviceName) {
        String query = "SELECT COUNT(*) FROM Service_Type WHERE Service_Type_Value = ?";
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, serviceName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ManagerImplement.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
        return false;
    }
      
      //MANAGE SERVICE//
}
