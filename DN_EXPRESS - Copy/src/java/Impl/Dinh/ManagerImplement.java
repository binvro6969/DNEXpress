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
import model.Manager;

/**
 *
 * @author haian
 */
public class ManagerImplement implements IRepository<Manager> {
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

    public Account viewManagerinfo(int id) {
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
                System.out.println("info: " + info);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(CustomerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }

    public boolean updateManagerInfo(Account account) {
        boolean test = false;
        String sql = "UPDATE [dbo].[Account] SET [Lname] = ?, [Fname] = ?, [Email] = ?, "
                + "[Phone] = ?, [Gender] = ?, [CCCD_number] = ?, [Dob] = ?"
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
                test = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ManagerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }
}
