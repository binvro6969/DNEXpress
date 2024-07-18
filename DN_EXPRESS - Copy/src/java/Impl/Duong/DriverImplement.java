/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.Duong;

import Interface.IRepository;
import context.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Driver;
import model.Driver_type;
import model.Post_Office;

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
    public List<Driver> find(Driver driver) {
        return null;

    }

    @Override
    public Boolean checkIdIsExist(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //DRIVER WALLET//
//    private DBcontext context;
    //Update số dư khi nạp tiền
    public void updateBalance(int driverId, int amount) {
        try (Connection conn = new DBcontext().getConnection()) {
            String sql = "UPDATE Driver SET Balance = balance + ? WHERE Driver_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, amount);
                ps.setInt(2, driverId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Truy vấn số dư
    public int getBalance(int driverId) {
        try (Connection conn = new DBcontext().getConnection()) {
            String sql = "SELECT Balance FROM Driver WHERE Driver_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, driverId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("Balance");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //DRIVER WALLET//
    //MANAGE DRIVER//
    public List<Driver_type> getAllDriverTypes() {
        List<Driver_type> list = new ArrayList<>();
        String query = "Select driver_type_id, driver_type_value from Driver_Type ";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int type_id = rs.getInt("driver_type_id");
                String type_value = rs.getString("driver_type_value");

                Driver_type driverType = new Driver_type(type_id, type_value);

                list.add(driverType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Driver> listDriverType(int driverTypeId) {
        List<Driver> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    Account.Account_id,\n"
                + "    Account.Lname,\n"
                + "    Account.Fname,\n"
                + "    Account.Email,\n"
                + "    Account.Phone,\n"
                + "    Account.Gender,\n"
                + "    Account.CCCD_number,\n"
                + "    Account.Avatar,\n"
                + "    Account.Dob,\n"
                + "    Account.Role_ID, \n"
                + "    Driver.License_number,\n"
                + "    Driver.driver_type_id,\n"
                + "    driver_type.driver_type_value,\n"
                + "    Driver.Post_Office_ID,\n"
                + "    Post_Office.Apartment_Number,\n"
                + "    Post_Office.Street_Name,\n"
                + "    Post_Office.District,\n"
                + "    Post_Office.Ward,\n"
                + "    Post_Office.City\n"
                + "FROM \n"
                + "    Account\n"
                + "INNER JOIN \n"
                + "    Driver ON Account.Account_id = Driver.Driver_ID\n"
                + "INNER JOIN\n"
                + "    driver_type ON Driver.driver_type_id = driver_type.driver_type_id\n"
                + "INNER JOIN\n"
                + "    Post_Office ON Driver.Post_Office_ID = Post_Office.Post_Office_ID\n"
                + "WHERE \n"
                + "    Account.Role_id = 2 AND Driver.driver_type_id = ?;";

        try (Connection con = new DBcontext().getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, driverTypeId);
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
                java.sql.Date dob = rs.getDate("Dob");
                int role = rs.getInt("Role_ID");
                String licenseNumber = rs.getString("License_number");
                //int driverTypeId = rs.getInt("driver_type_id");
                String driverTypeValue = rs.getString("driver_type_value");
                int idPO = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");

                Driver_type driverType = new Driver_type(driverTypeId, driverTypeValue);

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

                Post_Office postOffice = new Post_Office(idPO, apartmentNumber, streetName, district, ward, city);

                Driver driver = new Driver(account, licenseNumber, driverType, false);
                driver.setPost_office(postOffice);
                list.add(driver);
            }
        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Driver getDriverByID(int account_id) {
        Driver driver = null;

        String query = "SELECT a.*, d.License_number, d.driver_type_id, dt.driver_type_value, d.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City\n"
                + "FROM Account a\n"
                + "INNER JOIN Driver d ON a.Account_ID = d.Driver_ID\n"
                + "INNER JOIN Driver_type dt ON d.driver_type_id = dt.driver_type_id\n"
                + "INNER JOIN Post_Office po ON d.Post_Office_ID = po.Post_Office_ID\n"
                + "WHERE a.Account_ID = ?;";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, account_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int accountId = rs.getInt("Account_id");
                String lname = rs.getString("Lname");
                String fname = rs.getString("Fname");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                char gender = rs.getString("Gender").charAt(0);
                String cccdNumber = rs.getString("CCCD_number");
                String avatar = rs.getString("Avatar");
                java.sql.Date dob = rs.getDate("Dob");
                String licenseNumber = rs.getString("License_number");
                int role = rs.getInt("Role_ID");
                int driverTypeId = rs.getInt("driver_type_id");
                String driverTypeValue = rs.getString("driver_type_value");
                int idPO = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");
                Driver_type driver_type = new Driver_type(driverTypeId, driverTypeValue);

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

                Post_Office postOffice = new Post_Office(idPO, apartmentNumber, streetName, district, ward, city);

                driver = new Driver(account, licenseNumber, driver_type, false);
                driver.setPost_office(postOffice);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return driver;
    }

    public void addDriver(Driver driver, int driver_typeId, int postOfficeID) {
        String lastName = driver.getAccount().getLastName();
        String firstName = driver.getAccount().getFirstName();
        String email = driver.getAccount().getEmail();
        String phone = driver.getAccount().getPhone_numb();
        char gender = driver.getAccount().getGender().charAt(0);
        String cccd_numb = driver.getAccount().getCccd_numb();
        String avatar = driver.getAccount().getAvatar();
        java.sql.Date dob = driver.getAccount().getDob_Database();
        int role = driver.getAccount().getRole();
        String password = driver.getAccount().getPassword();

        String insertAccountQuery = "INSERT INTO Account (Lname, Fname, Email, Phone, Gender, CCCD_number, Avatar, Dob, Role_id, Password) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(insertAccountQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, String.valueOf(gender));
            ps.setString(6, cccd_numb);
            ps.setString(7, avatar);
            ps.setDate(8, dob);
            ps.setInt(9, role);
            ps.setString(10, password);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int accountId = 0;
            if (rs.next()) {
                accountId = rs.getInt(1);
            }

            String insertDriverQuery = "INSERT INTO Driver (Driver_ID, License_number, Driver_Type_ID, Post_Office_ID ) VALUES (?, ?, ?, ?)";

            PreparedStatement ps1 = conn.prepareStatement(insertDriverQuery);

            String license = driver.getLicense_numb();

            ps1.setInt(1, accountId);
            ps1.setString(2, license);
            ps1.setInt(3, driver_typeId);
            ps1.setInt(4, postOfficeID);

            ps1.executeUpdate();

            conn.close();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public void editDriver(Driver driver, int driver_typeId) {
        String lastName = driver.getAccount().getLastName();
        String firstName = driver.getAccount().getFirstName();
        String email = driver.getAccount().getEmail();
        String phone = driver.getAccount().getPhone_numb();
        char gender = driver.getAccount().getGender().charAt(0);
        String cccd_numb = driver.getAccount().getCccd_numb();
        String avatar = driver.getAccount().getAvatar();
        java.sql.Date dob = driver.getAccount().getDob_Database();
        int role = driver.getAccount().getRole();
        int account_id = driver.getAccount().getAccount_id();
        int idPO = driver.getPost_office().getPost_office_id();

        String insertAccountQuery = "UPDATE Account SET Lname = ?, Fname = ?, Email = ?, Phone = ?, Gender = ?, CCCD_number = ?, Avatar = ?, Dob = ?, Role_id = ? WHERE Account_ID = ?;";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(insertAccountQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, String.valueOf(gender));
            ps.setString(6, cccd_numb);
            ps.setString(7, avatar);
            ps.setDate(8, dob);
            ps.setInt(9, role);
            ps.setInt(10, account_id);
            ps.executeUpdate();

            String insertDriverQuery = "UPDATE Driver SET License_number = ?, Driver_Type_ID = ?, Post_Office_ID = ? WHERE Driver_ID = ?;";

            PreparedStatement ps1 = conn.prepareStatement(insertDriverQuery);

            String license = driver.getLicense_numb();

            ps1.setString(1, license);
            ps1.setInt(2, driver_typeId);
            ps1.setInt(3, idPO);
            ps1.setInt(4, account_id);
            ps1.executeUpdate();

            conn.close();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public void deleteDriver(int accountID) {
        String query = "DELETE FROM Driver WHERE Driver_ID = ?";

        String query1 = "DELETE FROM Account WHERE Account_ID = ?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, accountID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query1);

            ps.setInt(1, accountID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    public List<Driver> searchDrivers(String searchCriteria) {
        List<Driver> list = new ArrayList<>();
        String query = "SELECT a.*, d.License_number, d.driver_type_id, dt.driver_type_value, d.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City\n"
                + "FROM Account a\n"
                + "INNER JOIN Driver d ON a.Account_ID = d.Driver_ID\n"
                + "INNER JOIN Driver_type dt ON d.driver_type_id = dt.driver_type_id\n"
                + "INNER JOIN Post_Office po ON d.Post_Office_ID = po.Post_Office_ID\n"
                + "WHERE a.Account_ID = ? OR a.Lname LIKE ? OR a.Fname LIKE ?;";

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
                java.sql.Date dob = rs.getDate("Dob");
                String licenseNumber = rs.getString("License_number");
                int role = rs.getInt("Role_id");
                int driverTypeId = rs.getInt("driver_type_id");
                String driverTypeValue = rs.getString("driver_type_value");
                int idPO = rs.getInt("Post_Office_ID");
                String apartmentNumber = rs.getString("Apartment_Number");
                String streetName = rs.getString("Street_Name");
                String district = rs.getString("District");
                String ward = rs.getString("Ward");
                String city = rs.getString("City");

                Driver_type driver_type = new Driver_type(driverTypeId, driverTypeValue);

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

                Driver driver = new Driver(account, licenseNumber, driver_type, false);
                driver.setPost_office(postOffice);

                list.add(driver);
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
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);

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

    public boolean checkDriverTypeExists(int driverTypeId) {
        String query = "SELECT COUNT(*) FROM Driver_Type WHERE Driver_Type_ID = ?";
        try (Connection connection = new DBcontext().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, driverTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getCurrentImagePath(int accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String imagePath = null;

        try {
            conn = new DBcontext().getConnection(); // Thay thế bằng phương thức lấy kết nối tới CSDL của bạn

            String sql = "SELECT avatar FROM account WHERE account_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                imagePath = rs.getString("avatar");
            }
        } catch (Exception ex) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return imagePath;
    }

    //MANAGE DRIVER//
    
    
}
