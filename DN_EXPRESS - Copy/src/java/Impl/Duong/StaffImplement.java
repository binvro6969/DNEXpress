/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Duong;

import Impl.An.*;
import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Post_Office;
import model.Staff;

/**
 *
 * @author haian
 */
public class StaffImplement implements IRepository<Staff>{
//khai bao CustomerImplement và sử dụng có thể thêm các method khác không chỉnh sửa IRepository<>
    @Override
    public List<Staff> display(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Staff> find(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<Staff> listStaff() {
        List<Staff> list = new ArrayList<>();
        String query = "	SELECT \n" +
                    "    Account.Account_id,\n" +
                    "    Account.Lname,\n" +
                    "    Account.Fname,\n" +
                    "    Account.Email,\n" +
                    "    Account.Phone,\n" +
                    "    Account.Gender,\n" +
                    "    Account.CCCD_number,\n" +
                    "    Account.Avatar,\n" +
                    "    Account.Dob,\n" +
                    "	Account.Role_ID,\n" +
                    "    Post_Office.Post_Office_ID,\n" +
                    "    Post_Office.Apartment_Number,\n" +
                    "    Post_Office.Street_Name,\n" +
                    "    Post_Office.District,\n" +
                    "    Post_Office.Ward,\n" +
                    "    Post_Office.City\n" +
                    "FROM \n" +
                    "    Account\n" +
                    "INNER JOIN \n" +
                    "    Staff ON Account.Account_id = Staff.Staff_ID\n" +
                    "INNER JOIN \n" +
                    "    Post_Office ON Staff.Post_Office_ID = Post_Office.Post_Office_ID\n" +
                    "WHERE \n" +
                    "    Account.Role_ID = 3;";

        try (Connection con = new DBcontext().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt("Account_id");
                String lname = rs.getString("Lname");
                String fname = rs.getString("Fname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                char gender = rs.getString("Gender").charAt(0);
                String cccdNumber = rs.getString("CCCD_number");
                String avatar = rs.getString("Avatar");
                Date dob = rs.getDate("Dob");
                int role = rs.getInt("Role_id");
                int postOfficeId = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_number");
                String streetName = rs.getString("Street_name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");
                
                Post_Office post = new Post_Office(postOfficeId, apartmentNumber, streetName, district, ward, city);
                Account account = new Account(); 
                account.setAccount_id(accountId);
                account.setLastName(lname);
                account.setFirstName(fname);
                account.setEmail(email);
                account.setPhone_numb(phone);
                account.setGender(gender);
                account.setCccd_numb(cccdNumber);
                account.setAvatar(avatar);
                account.setDob(dob);
                account.setRole(role);
                
                Staff staff = new Staff(post, account);          
                list.add(staff);
            }
        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Staff getStaffById(int accountId) {
        Staff staff = null;
        String query = "SELECT a.Account_id, a.Lname, a.Fname, a.Email, a.Phone, a.Gender, a.CCCD_number, a.Avatar, a.Dob, a.Role_id, s.Staff_ID, p.Post_Office_ID, p.Apartment_number, p.Street_name, p.District, p.Ward, p.City " +
                       "FROM Account a " +
                       "INNER JOIN Staff s ON a.Account_id = s.Staff_ID " +
                       "INNER JOIN Post_Office p ON s.Post_Office_ID = p.Post_Office_ID " +
                       "WHERE a.Account_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
           
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
     
            if (rs.next()) {
                String lname = rs.getString("Lname");
                String fname = rs.getString("Fname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                char gender = rs.getString("Gender").charAt(0);
                String cccdNumber = rs.getString("CCCD_number");
                String avatar = rs.getString("Avatar");
                Date dob = rs.getDate("Dob");
                int role = rs.getInt("Role_id");
                int staffId = rs.getInt("Staff_ID");
                int postOfficeId = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_number");
                String streetName = rs.getString("Street_name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");
                
                Post_Office post = new Post_Office(postOfficeId, apartmentNumber, streetName, district, ward, city);
                Account account = new Account(); 
                account.setAccount_id(accountId);
                account.setLastName(lname);
                account.setFirstName(fname);
                account.setEmail(email);
                account.setPhone_numb(phone);
                account.setGender(gender);
                account.setCccd_numb(cccdNumber);
                account.setAvatar(avatar);
                account.setDob(dob);
                account.setRole(role);
                
               staff = new Staff(post, account);     
                
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();  
        }
        return staff;
    }
    
    public void addStaff(Staff staff, int postOfficeId) {
        String lastName = staff.getAccount().getLastName();
        String firstName = staff.getAccount().getFirstName();
        String email = staff.getAccount().getEmail();
        String phone = staff.getAccount().getPhone_numb();
        char gender = staff.getAccount().getGender().charAt(0);
        String cccdNumber = staff.getAccount().getCccd_numb();
        String avatar = staff.getAccount().getAvatar();
        Date dob = staff.getAccount().getDob_Database();
        int role = staff.getAccount().getRole();
        
        String insertAccountQuery = "INSERT INTO Account (Lname, Fname, Email, Phone, Gender, CCCD_number, Avatar, Dob, Role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(insertAccountQuery, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, String.valueOf(gender));
            ps.setString(6, cccdNumber);
            ps.setString(7, avatar);
            ps.setDate(8, dob);
            ps.setInt(9, role);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int accountId = 0;
            if (rs.next()) {
                accountId = rs.getInt(1);
            }
           
            String insertStaffQuery = "INSERT INTO Staff (Staff_ID, Post_Office_ID) VALUES (?, ?)";
            try (PreparedStatement ps1 = conn.prepareStatement(insertStaffQuery)) {
                ps1.setInt(1, accountId);
                ps1.setInt(2, postOfficeId);
                ps1.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();  
        }
    }
    
    public void editStaff(Staff staff, int postOfficeId) {
        String lastName = staff.getAccount().getLastName();
        String firstName = staff.getAccount().getFirstName();
        String email = staff.getAccount().getEmail();
        String phone = staff.getAccount().getPhone_numb();
        char gender = staff.getAccount().getGender().charAt(0);
        String cccdNumber = staff.getAccount().getCccd_numb();
        String avatar = staff.getAccount().getAvatar();
        Date dob = staff.getAccount().getDob_Database();
        int role = staff.getAccount().getRole();
        int accountId = staff.getAccount().getAccount_id();

        String updateAccountQuery = "UPDATE Account SET Lname = ?, Fname = ?, Email = ?, Phone = ?, Gender = ?, CCCD_number = ?, Avatar = ?, Dob = ?, Role_id = ? WHERE Account_ID = ?";

        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(updateAccountQuery)) {
            
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, String.valueOf(gender));
            ps.setString(6, cccdNumber);
            ps.setString(7, avatar);
            ps.setDate(8, dob);
            ps.setInt(9, role);
            ps.setInt(10, accountId);

            int rowsAffected = ps.executeUpdate(); 

            if (rowsAffected > 0) {
                String updateStaffQuery = "UPDATE Staff SET Post_Office_ID = ? WHERE Staff_ID = ?";
                try (PreparedStatement ps1 = conn.prepareStatement(updateStaffQuery)) {
                    ps1.setInt(1, postOfficeId);
                    ps1.setInt(2, accountId);
                    ps1.executeUpdate();
                }
                System.out.println("Staff updated successfully.");
            } else {
                System.out.println("No staff account updated.");
            }
        } catch (SQLException e) {
            System.out.println("Error in editStaff method: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void deleteStaff(int accountId) {
        String deleteStaffQuery = "DELETE FROM Staff WHERE Staff_ID = ?";
        String deleteAccountQuery = "DELETE FROM Account WHERE Account_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBcontext().getConnection();
            conn.setAutoCommit(false);
            
            ps = conn.prepareStatement(deleteStaffQuery);
            ps.setInt(1, accountId);
            ps.executeUpdate();
            
            ps = conn.prepareStatement(deleteAccountQuery);
            ps.setInt(1, accountId);
            ps.executeUpdate();
            
            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
           
        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean checkStaff(int accountId) {
        String query = "SELECT * FROM Staff WHERE Staff_ID = ?";
        try (Connection conn = new DBcontext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) { 
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public List<Staff> searchStaff(String searchCriteria) {
        List<Staff> list = new ArrayList<>();
        String query = "SELECT a.Account_id, a.Lname, a.Fname, a.Email, a.Phone, a.Gender, a.CCCD_number, a.Avatar, a.Dob, a.Role_ID, " +
                       "po.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City " +
                       "FROM Account a " +
                       "INNER JOIN Staff s ON a.Account_ID = s.Staff_ID " +
                       "INNER JOIN Post_Office po ON s.Post_Office_ID = po.Post_Office_ID " +
                       "WHERE a.Account_ID = ? OR a.Lname LIKE ? OR a.Fname LIKE ?;";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

        
            int accountId;
            try {
                accountId = Integer.parseInt(searchCriteria);
            } catch (NumberFormatException e) {
                accountId = -1;
            }

            ps.setInt(1, accountId);
            ps.setString(2, "%" + searchCriteria + "%");
            ps.setString(3, "%" + searchCriteria + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int retrievedAccountId = rs.getInt("Account_id");
                String lname = rs.getString("Lname");
                String fname = rs.getString("Fname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                char gender = rs.getString("Gender").charAt(0);
                String cccdNumber = rs.getString("CCCD_number");
                String avatar = rs.getString("Avatar");
                Date dob = rs.getDate("Dob");
                int role = rs.getInt("Role_ID");
                int idPO = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");

                Account account = new Account();
                
                account.setAccount_id(retrievedAccountId);
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setEmail(email);
                account.setGender(gender);
                account.setPhone_numb(phone);
                account.setDob(dob);
                account.setCccd_numb(cccdNumber);
                account.setAvatar(avatar);
                account.setRole(role);
                
                Post_Office postOffice = new Post_Office(idPO, apartmentNumber, streetName, district, ward, city);

                Staff staff = new Staff(postOffice, account); 
                
                
                list.add(staff);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return list;
    }
     
     public boolean checkPostOfficeExists(int postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
          
            conn = new DBcontext().getConnection();

           
            String sql = "SELECT COUNT(*) AS count FROM Post_Office WHERE Post_Office_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();

  
            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
          
        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }
}
