/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.An;

import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class GeneralImplement implements IRepository<Account>{
   
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
        String fname = account.getFirstName();
        String lname = account.getLastName();
        String phone_numb = account.getPhone_numb();
        String email = account.getEmail();
        String password = account.getPassword();
        int role = 1;
        String sqlQuerry_add = "insert Account(Lname,Fname,Email,Phone,Password,Role_id) values(?,?,?,?,?,?);";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_add,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,lname );
            ps.setString(2, fname);
            ps.setString(3, email);
            ps.setString(4, phone_numb);
            ps.setString(5, password);
            ps.setInt(6, role);           
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int account_ID=0;
            if (rs.next()) {
                account_ID =rs.getInt(1);
            }
            String sqlQuerry_add_cus="insert Customer (Customer_ID) values (?);";
            PreparedStatement pscus = conn.prepareStatement(sqlQuerry_add_cus);
            pscus.setInt(1, account_ID);
            pscus.executeUpdate();
            conn.close();    
        } catch (Exception e) {
            System.out.println("Error");
        }
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
    public List<Account> find(Account account) {
        String sqlQuerry_find ="Select Account_id,Lname,Fname,Phone,Password,Email,Role_id from Account where Phone=? and Password=? and Role_id=?";
        List<Account> list = new ArrayList<>();
        String phone_numb_check= account.getPhone_numb();
        String pass=account.getPassword();
        int role_check =account.getRole();
       try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, phone_numb_check);
            ps.setString(2, pass);
            ps.setInt(3, role_check);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int account_id=rs.getInt(1);
                String lname=rs.getString(2);
                String fname=rs.getString(3);                
                String phone_numb =rs.getString(4);               
                String password=rs.getString(5);
                String email=rs.getString(6);
                int role =rs.getInt(7);
                account = new Account();
                account.setAccount_id(account_id);
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setEmail(email);
                account.setPassword(password);
                account.setPhone_numb(phone_numb);
                account.setRole(role);
                list.add(account);
                 //chuyển từ sql date sang java.until.date
//                Date dobRaw = new java.util.Date(dob_database.getTime());
//                //đổi format cho date
//                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
//                //đổi format cho date
//                String formattedDateStr = outputFormat.format(dobRaw);
//                //đổi format cho date
//                Date dob = outputFormat.parse(formattedDateStr);
//                //tạo đối tượng                
//                account = new Account(id_account, Fname, lname, emai, password, phone,dob , cccd_numb, avatar, Gender, role);
//                //thêm vào danh sách 
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");          
        }
       return list;
    }
    public boolean changePassword(Account account){
        String SQLquerry = "UPDATE Account set Password=? where Account_ID=?";
        try {           
            String password = account.getPassword();
            int account_id= account.getAccount_id();
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquerry);
            ps.setString(1, password);
            ps.setInt(2, account_id);
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected!=0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Account getAccountinfor(int account_id){
        Account account = new Account();
        String SQL_querry="SELECT Fname,Lname,Phone FROM Account WHERE Account_ID=?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, account_id);                      
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String phone = rs.getString(3);
                account.setAccount_id(account_id);
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setPhone_numb(phone);               
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");          
        }
        return account;
    }
    @Override
    public Boolean checkIdIsExist(Account object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
