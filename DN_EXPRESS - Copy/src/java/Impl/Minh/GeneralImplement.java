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

/**
 *
 * @author haian
 */
public class GeneralImplement implements IRepository<Account> {

    public List<Account> findByEmail(Account account){
        String sqlQuerry_find ="SELECT Account_ID,Email FROM Account WHERE Email=?";
        List<Account> list = new ArrayList<>();
        String check_email= account.getEmail();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, check_email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int account_id= rs.getInt(1);
                String email = rs.getString(2);
                account.setAccount_id(account_id);
                account.setEmail(email);
                list.add(account);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        } 
        return list;
    }

    public void addnewAccountWithGoogle(Account account ){
        String email= account.getEmail();        
       
        String sqlQuerry_add = "insert Account(Email,Role_id) values(?,1);";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_add);
            ps.setString(1, email);
            ps.executeUpdate();
            conn.close();    
        } catch (Exception e) {
            System.out.println("Error");
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

    public Account getAccountByAccID(String accountID) {
        Account acc = new Account();
        String sqlQuery = "SELECT Account_ID, Fname, Lname, Email, Phone, Gender, Password, CCCD_number, Avatar, Dob, Role_ID, Status FROM Account WHERE Account_ID = ?";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setString(1, accountID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("Account_ID");
                    String firstName = rs.getString("Fname");
                    String lastName = rs.getString("Lname");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("Phone");
                    char gender = rs.getString("Gender").charAt(0);
                    String password = rs.getString("Password");
                    String cccdNumber = rs.getString("CCCD_number");
                    String avatar = rs.getString("Avatar");
                    Date dob = rs.getDate("Dob");
                    int role = rs.getInt("Role_ID");
                    boolean status = rs.getBoolean("Status");

                    acc.setAccount_id(accountId);
                    acc.setFirstName(firstName);
                    acc.setLastName(lastName);
                    acc.setEmail(email);
                    acc.setPassword(password);
                    acc.setPhone_numb(phoneNumber);
                    acc.setDob(dob);
                    acc.setCccd_numb(cccdNumber);
                    acc.setAvatar(avatar);
                    acc.setGender(gender);
                    acc.setRole(role);
                    acc.setStatus(status);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return acc;
    }

}
