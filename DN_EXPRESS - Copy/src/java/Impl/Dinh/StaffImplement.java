/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Dinh;

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
import model.Post_Office;
import model.Staff;

/**
 *
 * @author haian
 */
public class StaffImplement implements IRepository<Staff> {
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

    public boolean updateStaffInfo(Staff staff) {
        boolean test = false;
        String sql = "UPDATE [dbo].[Account] SET [Lname] = ?, [Fname] = ?, [Email] = ?, "
                + "[Phone] = ?, [Gender] = ?, [CCCD_number] = ?, [Dob] = ? "
                + "WHERE [Account_ID] = ?";

        String sql1 = "UPDATE [dbo].[Staff] "
                + "SET [Post_Office_ID] = ? "
                + "WHERE [Staff_ID] = ?";

        char gender = staff.getAccount().getGender().charAt(0);
        try {
            Connection con = new DBcontext().getConnection();
            PreparedStatement psAccount = con.prepareStatement(sql);
            psAccount.setString(1, staff.getAccount().getLastName());
            psAccount.setString(2, staff.getAccount().getFirstName());
            psAccount.setString(3, staff.getAccount().getEmail());
            psAccount.setString(4, staff.getAccount().getPhone_numb());
            psAccount.setString(5, String.valueOf(gender));
            psAccount.setString(6, staff.getAccount().getCccd_numb());
            psAccount.setDate(7, staff.getAccount().getDob_Database());
            psAccount.setInt(8, staff.getAccount().getAccount_id());
            psAccount.executeUpdate();

            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, staff.getPost_office().getPost_office_id());
            ps1.setInt(2, staff.getAccount().getAccount_id());
            ps1.executeUpdate();

            if (psAccount.executeUpdate() == 1 && ps1.executeUpdate() == 1) {
                return test = true;
            }
        } catch (SQLException ex) {

        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

    public Staff viewStaffInfo(int id) {
        Staff staff = new Staff();
        String sql = "SELECT \n"
                + "    A.Lname, \n"
                + "    A.Fname, \n"
                + "    A.Email, \n"
                + "    A.Phone, \n"
                + "    A.Gender, \n"
                + "    A.CCCD_number, \n"
                + "    A.Dob, \n"
                + "    A.Avatar, \n"
                + "    PO.City\n"
                + "FROM \n"
                + "    Account A\n"
                + "INNER JOIN \n"
                + "    Staff S ON A.Account_ID = S.Staff_ID\n"
                + "INNER JOIN \n"
                + "    Post_Office PO ON S.Post_Office_ID = PO.Post_Office_ID\n"
                + "WHERE \n"
                + "    A.Account_ID = ?";

        try {
            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String lname = rs.getString("Lname");
                String fname = rs.getString("Fname");
                String email = rs.getString("Email");
                String phoneNum = rs.getString("Phone");
                char gender = rs.getString("Gender").charAt(0);
                String cccdNumber = rs.getString("CCCD_number");
                Date dob = rs.getDate("Dob");
                String avatar = rs.getString("Avatar");
                String city = rs.getString("City");
                Account account = new Account();
//            fname, lname, email, phoneNum, dob, cccdNumber, gender
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setEmail(email);
                account.setPhone_numb(phoneNum);
                account.setDob(dob);
                account.setCccd_numb(cccdNumber);
                account.setGender(gender);
                account.setAvatar(avatar);
                Post_Office postOffice = new Post_Office();
                postOffice = getPostOfficeFromCity(city);
                
                staff.setAccount(account);
                staff.setPost_office(postOffice);

            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(StaffImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staff;
    }

    public List<Post_Office> getAllPostOffices() {
        List<Post_Office> postOffices = new ArrayList<>();
        String SQL_query = "SELECT Post_Office_ID, Apartment_Number, Street_Name, District, Ward, City FROM Post_Office";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post_Office post_Office = new Post_Office();
                int post_Office_id = rs.getInt(1);
                String apartment_Number = rs.getString(2);
                String street_Name = rs.getString(3);
                String district = rs.getString(4);
                String ward = rs.getString(5);
                String city = rs.getString(6);
                post_Office.setPost_office_id(post_Office_id);
                post_Office.setApartmentNumber(apartment_Number);
                post_Office.setStreetName(street_Name);
                post_Office.setDistrict(district);
                post_Office.setWard(ward);
                post_Office.setCity(city);
                postOffices.add(post_Office);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postOffices;
    }

    public Post_Office getPostOfficeFromCity(String city_check) {
        Post_Office post_Office = new Post_Office();
        String SQL_querry = "SELECT Post_Office_ID,Apartment_Number,Street_Name,District,Ward,City FROM Post_Office where City=?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setString(1, city_check);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int post_Office_id = rs.getInt(1);
                String apartment_Number = rs.getString(2);
                String street_Name = rs.getString(3);
                String district = rs.getString(4);
                String ward = rs.getString(5);
                String city = rs.getString(6);
                post_Office.setPost_office_id(post_Office_id);
                post_Office.setApartmentNumber(apartment_Number);
                post_Office.setStreetName(street_Name);
                post_Office.setDistrict(district);
                post_Office.setWard(ward);
                post_Office.setCity(city);
            }
            conn.close();
        } catch (Exception e) {
        }
        return post_Office;
    }

}
