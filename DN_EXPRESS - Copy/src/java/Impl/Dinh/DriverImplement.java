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
import model.Driver;

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

    public Driver viewDriverInfo(int id) {
       Driver dInfo = new Driver();
        String sql = "SELECT A.[Lname], A.[Fname], A.[Email], A.[Phone], A.[Gender], A.[CCCD_number], A.[Dob], A.[Avatar], "
                + "D.[License_number] "
                + "FROM [dbo].[Account] A "
                + "JOIN [dbo].[Driver] D ON A.[Account_ID] = D.[Driver_ID] "
                + "WHERE A.[Account_ID] = ?";
        try (Connection con = new DBcontext().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
                String licenseNumber = rs.getString("License_number");
                String avatar = rs.getString("Avatar");
//                fname, lname, email, phoneNum, dob, cccdNumber, gender
                // Create Account object
                Account account = new Account();
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setEmail(email);
                account.setPhone_numb(phoneNum);
                account.setDob(dob);
                account.setCccd_numb(cccdNumber);
                account.setGender(gender);
                account.setAvatar(avatar);
                // Create Driver object and add to list
                dInfo.setAccount(account);
                dInfo.setLicense_numb(licenseNumber);
               
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dInfo;
    }

    public boolean updateDriverInfo(Driver driver) {
        String sql = "UPDATE [dbo].[Account] "
                + "SET [Lname] = ?, [Fname] = ?, [Email] = ?, [Phone] = ?, [Gender] = ?, [CCCD_number] = ?, [Dob] = ? "
                + "WHERE [Account_ID] = ?";

        String sql1 = "UPDATE [dbo].[Driver] "
                + "SET [License_number] = ? "
                + "WHERE [Driver_ID] = ?";
        try {
            System.out.println("gender: " + String.valueOf(driver.getAccount().getGender().charAt(0)));
            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, driver.getAccount().getLastName());
            ps.setString(2, driver.getAccount().getFirstName());
            ps.setString(3, driver.getAccount().getEmail());
            ps.setString(4, driver.getAccount().getPhone_numb());
            ps.setString(5, String.valueOf(driver.getAccount().getGender().charAt(0)));
            ps.setString(6, driver.getAccount().getCccd_numb());
            ps.setDate(7, driver.getAccount().getDob_Database());
            ps.setInt(8, driver.getAccount().getAccount_id());
            ps.executeUpdate();
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1, driver.getLicense_numb());
            ps1.setInt(2, driver.getAccount().getAccount_id());
            ps1.executeUpdate();

            if (ps.executeUpdate() == 1 && ps1.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
