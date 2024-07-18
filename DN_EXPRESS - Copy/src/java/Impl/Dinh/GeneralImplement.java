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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author haian
 */
public class GeneralImplement implements IRepository<Account> {

    public List<Account> findByEmail(Account account) {
        String sqlQuerry_find = "SELECT Account_ID,Lname,Fname,Email,Phone,Gender,Password,CCCD_number,Avatar,Dob,Role FROM Account WHERE Email=?";
        List<Account> list = new ArrayList<>();
        String check_email = account.getEmail();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, check_email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_account = rs.getInt(1);
                String lname = rs.getString(2);
                String Fname = rs.getString(3);
                String emai = rs.getString(4);
                String phone = rs.getString(5);
                char Gender = rs.getString(6).charAt(0);
                String password = rs.getString(7);
                String cccd_numb = rs.getString(8);
                String avatar = rs.getString(9);
                java.sql.Date dob_database = rs.getDate(10);
                int role = rs.getInt(11);

                //chuyển từ sql date sang java.until.date
                Date dobRaw = new java.util.Date(dob_database.getTime());
                //đổi format cho date
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                //đổi format cho date
                String formattedDateStr = outputFormat.format(dobRaw);
                //đổi format cho date
                Date dob = outputFormat.parse(formattedDateStr);
                //tạo đối tượng                

                //thêm vào danh sách 
                list.add(account);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void addnewAccountWithGoogle(Account account) {
        String email = account.getEmail();
        String fistname = account.getFirstName();
        String lastname = account.getLastName();

        int role = 1;
        String sqlQuerry_add = "insert Account(Lname,Fname,Email,Role_id,Avatar) values(?,?,?,?,?);";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_add);
            ps.setString(1, lastname);
            ps.setString(2, fistname);
            ps.setString(3, email);
            ps.setInt(4, role);

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<Account> display(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addNew(Account account) {

    }

    @Override
    public void edit(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Account> find(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean checkIdIsExist(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateAvatar(Account account) {
        boolean test = false;
        String sql = "UPDATE [dbo].[Account] SET [Avatar] = ? "
                + "WHERE [Account_ID] = ?";
        try {
            Connection con = new DBcontext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getAvatar());
            ps.setInt(2, account.getAccount_id());
            ps.executeUpdate();
            if (ps.executeUpdate() == 1) {
                return test = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

    public boolean updatePassword(int accountId, String newPassword) {
        String sql = "UPDATE [dbo].[Account] SET [Password] = ? WHERE [Account_ID] = ?";
        try (Connection con = new DBcontext().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setInt(2, accountId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated == 1;

        } catch (Exception ex) {
            Logger.getLogger(GeneralImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Account viewAccountinfo(int id) {
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
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            Logger.getLogger(CustomerImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }

}
