/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl.An;

import Interface.IRepository;
import context.DBcontext;
import static google_context.JSONReader.fetchJSONFromURL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.Driver;
import model.Driver_type;
import model.Invoice;
import model.Order_Status;
import model.Package_Detail;
import model.Package_Size;
import model.Package_Type;
import model.Post_Office;
import model.ShipmentOrder_Invoice_Order_Status;
import model.Shipment_Order;
import model.Shipment_Order_Driver;
import org.json.JSONArray;
import org.json.JSONObject;

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
        String sqlQuerry_find = "SELECT \n"
                + "    a.Account_ID,\n"
                + "    a.Lname,\n"
                + "    a.Fname,\n"
                + "    a.Phone,\n"
                + "    a.Password,\n"
                + "    a.Role_id,\n"
                + "    d.driver_type_id,\n"
                + "    d.Online_Status\n"
                + "FROM \n"
                + "    Account a\n"
                + "JOIN \n"
                + "    Driver d ON a.Account_ID = d.Driver_ID\n"
                + "where Phone =? and Password =?";
        List<Driver> list = new ArrayList<>();
        String phone_numb_check = driver.getAccount().getPhone_numb();
        String pass = driver.getAccount().getPassword();
        int role_check = driver.getAccount().getRole();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry_find);
            ps.setString(1, phone_numb_check);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int account_id = rs.getInt(1);
                String lname = rs.getString(2);
                String fname = rs.getString(3);
                String phone_numb = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                int driver_type_id = rs.getInt(7);
                boolean online_status = rs.getBoolean(8);
                Driver_type driver_type = new Driver_type(driver_type_id);

                Account account = new Account();
                account.setAccount_id(account_id);
                account.setFirstName(fname);
                account.setLastName(lname);
                account.setPhone_numb(phone_numb);
                account.setPassword(password);

                driver = new Driver();
                driver.setAccount(account);
                driver.setDriver_type(driver_type);
                driver.setStatus(online_status);

                list.add(driver);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return list;
    }

    @Override
    public Boolean checkIdIsExist(Driver object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateActiveStatusOn(Driver driver) {
        String SQLquery = "UPDATE Driver set Status=1 where Driver_ID=?;";
        try {
            int id_Driver = driver.getAccount().getAccount_id();
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquery);
            ps.setInt(1, id_Driver);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return false;
    }
    
    
    

    public boolean updateActiveStatusOff(Driver driver) {
        String SQLquerry = "UPDATE Driver set Status=0 where Driver_ID=?;";
        try {
            int id_Driver = driver.getAccount().getAccount_id();
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquerry);
            ps.setInt(1, id_Driver);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return false;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return true;
    }
    
    public boolean updateIsWorkingSOn(int id_Driver) {
        String SQLquery = "UPDATE Driver set IsWorking=1 where Driver_ID=?;";
        try {
            
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquery);
            ps.setInt(1, id_Driver);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return false;
    }
    
    public boolean updateIsWorkingOff(int id_Driver) {
        String SQLquerry = "UPDATE Driver set IsWorking=0 where Driver_ID=?;";
        try {            
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQLquerry);
            ps.setInt(1, id_Driver);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return false;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return true;
    }
    
    public List<Driver> findLocation (String city){
        
      
        Post_Office post_office = new Post_Office();
        List<Driver> list = new ArrayList<>(); String SQL_querry_findPostOffice ="SELECT Post_Office_ID from Post_Office where City =?";
        String SQL_querry_location = "SELECT Driver.Driver_ID,Driver.Latitude, Driver.Longitude\n"
                + "FROM Driver\n"
                + "JOIN Post_Office ON Driver.Post_Office_ID = Post_Office.Post_Office_ID\n"
                + "where Driver.Post_Office_ID=? and Driver.Online_Status=1 and Driver.Driver_Type_ID=1 and Driver.IsWorking=0";
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findPostOffice);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                int post_office_id= rs.getInt(1);
                post_office.setPost_office_id(post_office_id);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_location);
            ps.setInt(1, post_office.getPost_office_id());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int driver_id=rs.getInt(1);
                float latitude=rs.getFloat(2);
                float longitude=rs.getFloat(3);
                Driver driver = new Driver();
                Account account = new Account();
                account.setAccount_id(driver_id);               
                driver.setAccount(account);
                driver.setLatitude(latitude);
                driver.setLongitude(longitude);
                list.add(driver);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        
        return list;
    }
    
    public List<Driver> findLocationWhenCancel (String city,int driver_id_check){
        
      
        Post_Office post_office = new Post_Office();
        List<Driver> list = new ArrayList<>(); String SQL_querry_findPostOffice ="SELECT Post_Office_ID from Post_Office where City =?";
        String SQL_querry_location = "SELECT Driver.Driver_ID, Driver.Latitude, Driver.Longitude\n"
                + "FROM Driver\n"
                + "JOIN Post_Office ON Driver.Post_Office_ID = Post_Office.Post_Office_ID\n"
                + "WHERE Driver.Post_Office_ID=? \n"
                + "  AND Driver.Online_Status=1 \n"
                + "  AND Driver.Driver_Type_ID=1 \n"
                + "  AND Driver.IsWorking=0\n"
                + "  AND Driver.Driver_ID <> ?;";
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findPostOffice);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){               
                int post_office_id= rs.getInt(1);
                post_office.setPost_office_id(post_office_id);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_location);
            ps.setInt(1, post_office.getPost_office_id());
            ps.setInt(2, driver_id_check);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int driver_id=rs.getInt(1);
                float latitude=rs.getFloat(2);
                float longitude=rs.getFloat(3);
                Driver driver = new Driver();
                Account account = new Account();
                account.setAccount_id(driver_id);               
                driver.setAccount(account);
                driver.setLatitude(latitude);
                driver.setLongitude(longitude);
                list.add(driver);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        
        return list;
    }

    public List<Driver> findRating(String city) {
        List<Driver> list = new ArrayList<>();
        String SQL_querry_findPostOffice = "SELECT Post_Office_ID from Post_Office where City =?";
        String SQL_querry_rating = "SELECT Driver.Driver_ID, Driver.Rating\n"
                + "FROM Driver\n"
                + "JOIN Post_Office ON Driver.Post_Office_ID = Post_Office.Post_Office_ID\n"
                + "where Driver.Post_Office_ID=? and Driver.Online_Status=1 and Driver.Driver_Type_ID=1 and Driver.IsWorking=0";
        Post_Office post_office = new Post_Office();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findPostOffice);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int post_office_id = rs.getInt(1);
                post_office.setPost_office_id(post_office_id);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_rating);
            ps.setInt(1, post_office.getPost_office_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int driver_id = rs.getInt(1);
                float rating = rs.getFloat(2);
                Driver driver = new Driver();
                Account account = new Account();
                account.setAccount_id(driver_id);
                driver.setAccount(account);
                driver.setRating(rating);
                list.add(driver);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return list;
    }
    
    public List<Driver> findRatingWhenCancel(String city,int driver_id_check) {
        List<Driver> list = new ArrayList<>();
        String SQL_querry_findPostOffice = "SELECT Post_Office_ID from Post_Office where City =?";
        String SQL_querry_rating = "SELECT Driver.Driver_ID, Driver.Rating\n"
                + "FROM Driver\n"
                + "JOIN Post_Office ON Driver.Post_Office_ID = Post_Office.Post_Office_ID\n"
                + "WHERE Driver.Post_Office_ID=? \n"
                + "  AND Driver.Online_Status=1 \n"
                + "  AND Driver.Driver_Type_ID=1 \n"
                + "  AND Driver.IsWorking=0\n"
                + "  AND Driver.Driver_ID <> ?;";
        Post_Office post_office = new Post_Office();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findPostOffice);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int post_office_id = rs.getInt(1);
                post_office.setPost_office_id(post_office_id);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_rating);
            ps.setInt(1, post_office.getPost_office_id());
            ps.setInt(2, driver_id_check);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int driver_id = rs.getInt(1);
                float rating = rs.getFloat(2);
                Driver driver = new Driver();
                Account account = new Account();
                account.setAccount_id(driver_id);
                driver.setAccount(account);
                driver.setRating(rating);
                list.add(driver);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return list;
    }
    
    public Driver chooseDriver(String origins, String city) {
        String apiKey = "AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0";
        double orgin_lat = 0;
        double orgin_lng = 0;
        try {
            String origin_encode = URLEncoder.encode(origins, StandardCharsets.UTF_8.toString());
            String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + origin_encode + ";&key=" + apiKey;
            JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
            if (jsonData != null) {
                JSONArray results = jsonData.getJSONArray("results");
                if (results.length() > 0) {
                    JSONObject firstResult = results.getJSONObject(0);
                    JSONObject geometry = firstResult.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    orgin_lat = location.getDouble("lat");
                    orgin_lng = location.getDouble("lng");
                }
            }

        } catch (Exception e) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
        }

        Driver choosedriver = new Driver();
        List<Driver> driverLocation = findLocation(city);
        List<Driver> driverRating = findRating(city);
        Map<Integer, Integer> driverDistanceMap = new HashMap<>();
        Map<Integer, Float> driverRatingMap = new HashMap<>();

        StringBuilder destinationsBuilder = new StringBuilder();
        for (Driver driver : driverLocation) {
            if (destinationsBuilder.length() > 0) {
                destinationsBuilder.append("%7C");
            }
            destinationsBuilder.append(driver.getLatitude());
            destinationsBuilder.append(",");
            destinationsBuilder.append(driver.getLongitude());
        }
        String destinations = destinationsBuilder.toString();
        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?&destinations=" + destinations + "&origins=" + orgin_lat +","+orgin_lng+ "&key=" + apiKey;
        try {
            JSONObject jsonData = fetchJSONFromURL(urlString);
            if (jsonData != null) {
                JSONArray rows = jsonData.getJSONArray("rows");
                for (int i = 0; i < rows.length(); i++) {
                    JSONObject row = rows.getJSONObject(i);
                    JSONArray elements = row.getJSONArray("elements");
                    for (int j = 0; j < elements.length(); j++) {
                        JSONObject element = elements.getJSONObject(j);
                        JSONObject distance = element.getJSONObject("distance");
                        int distance_value = distance.getInt("value");
                        Driver driver = driverLocation.get(j);
                        driverDistanceMap.put(driver.getAccount().getAccount_id(), distance_value);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching or parsing JSON: " + e.getMessage());
        }
        if (!driverDistanceMap.isEmpty()) {
            for (Driver driver : driverRating) {
                driverRatingMap.put(driver.getAccount().getAccount_id(), driver.getRating());
            }
            Map<Integer, Double> weightDistanceMap = new HashMap<>();
            Map<Integer, Double> weightRatingMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : driverDistanceMap.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                double weigh_value = 0;
                if (value <= 2000) {
                    weigh_value = 1 - (value / 2000);
                }
                if (value <= 5000 && value > 2000) {
                    weigh_value = 1 - (value / 5000);
                }
                if (value <= 7000 && value > 5000) {
                    weigh_value = 1 - (value / 7000);
                }
                if (value <= 10000 && value > 7000) {
                    weigh_value = 1 - (value / 10000);
                }
                if (value > 10000) {
                    weigh_value = 0;
                }
                weightDistanceMap.put(key, weigh_value);
            }
            for (Map.Entry<Integer, Float> entry : driverRatingMap.entrySet()) {
                Integer key = entry.getKey();
                Float value = entry.getValue();
                double weigh_value = value;
                weightRatingMap.put(key, weigh_value);
            }
            Map<Integer, Double> combinedWeightsMap = new HashMap<>();
            for (Map.Entry<Integer, Double> entry : weightDistanceMap.entrySet()) {
                Integer key = entry.getKey();
                Double distanceWeight = entry.getValue();
                Double ratingWeight = weightRatingMap.get(key);
                if (ratingWeight != null) {
                    double combineWeigh = distanceWeight + ratingWeight;
                    combinedWeightsMap.put(key, combineWeigh);
                }
            }
            double maxValue = Double.MIN_VALUE;
            for (double value : combinedWeightsMap.values()) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            Integer maxKey = null;
            for (Map.Entry<Integer, Double> entry : combinedWeightsMap.entrySet()) {
                if (entry.getValue() == maxValue) {
                    maxKey = entry.getKey();
                    break;
                }
            }
            Account chooseaccount = new Account();
            chooseaccount.setAccount_id(maxKey);
            choosedriver.setAccount(chooseaccount);
        }
        return choosedriver;
    }
    
    //methold Chuyển driver khi huỷ đơn 
    public Driver chooseDriverWhenCancel(String origins, String city,int driver_id_check) {
        String apiKey = "AIzaSyA1mLLNrhFhndWA119ZTywc38Fyw9cyas0";
        double orgin_lat = 0;
        double orgin_lng = 0;
        try {
            String origin_encode = URLEncoder.encode(origins, StandardCharsets.UTF_8.toString());
            String pick_up_urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + origin_encode + ";&key=" + apiKey;
            JSONObject jsonData = fetchJSONFromURL(pick_up_urlString);
            if (jsonData != null) {
                JSONArray results = jsonData.getJSONArray("results");
                if (results.length() > 0) {
                    JSONObject firstResult = results.getJSONObject(0);
                    JSONObject geometry = firstResult.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    orgin_lat = location.getDouble("lat");
                    orgin_lng = location.getDouble("lng");
                }
            }

        } catch (Exception e) {
            Logger.getLogger(DriverImplement.class.getName()).log(Level.SEVERE, null, e);
        }

        Driver choosedriver = new Driver();
        List<Driver> driverLocation = findLocationWhenCancel(city,driver_id_check);
        List<Driver> driverRating = findRatingWhenCancel(city,driver_id_check);
        Map<Integer, Integer> driverDistanceMap = new HashMap<>();
        Map<Integer, Float> driverRatingMap = new HashMap<>();

        StringBuilder destinationsBuilder = new StringBuilder();
        for (Driver driver : driverLocation) {
            if (destinationsBuilder.length() > 0) {
                destinationsBuilder.append("%7C");
            }
            destinationsBuilder.append(driver.getLatitude());
            destinationsBuilder.append(",");
            destinationsBuilder.append(driver.getLongitude());
        }
        String destinations = destinationsBuilder.toString();
        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?&destinations=" + destinations + "&origins=" + orgin_lat +","+orgin_lng+ "&key=" + apiKey;
        try {
            JSONObject jsonData = fetchJSONFromURL(urlString);
            if (jsonData != null) {
                JSONArray rows = jsonData.getJSONArray("rows");
                for (int i = 0; i < rows.length(); i++) {
                    JSONObject row = rows.getJSONObject(i);
                    JSONArray elements = row.getJSONArray("elements");
                    for (int j = 0; j < elements.length(); j++) {
                        JSONObject element = elements.getJSONObject(j);
                        JSONObject distance = element.getJSONObject("distance");
                        int distance_value = distance.getInt("value");
                        Driver driver = driverLocation.get(j);
                        driverDistanceMap.put(driver.getAccount().getAccount_id(), distance_value);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching or parsing JSON: " + e.getMessage());
        }
        if (!driverDistanceMap.isEmpty()) {
            for (Driver driver : driverRating) {
                driverRatingMap.put(driver.getAccount().getAccount_id(), driver.getRating());
            }
            Map<Integer, Double> weightDistanceMap = new HashMap<>();
            Map<Integer, Double> weightRatingMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : driverDistanceMap.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                double weigh_value = 0;
                if (value <= 2000) {
                    weigh_value = 1 - (value / 2000);
                }
                if (value <= 5000 && value > 2000) {
                    weigh_value = 1 - (value / 5000);
                }
                if (value <= 7000 && value > 5000) {
                    weigh_value = 1 - (value / 7000);
                }
                if (value <= 10000 && value > 7000) {
                    weigh_value = 1 - (value / 10000);
                }
                if (value > 10000) {
                    weigh_value = 0;
                }
                weightDistanceMap.put(key, weigh_value);
            }
            for (Map.Entry<Integer, Float> entry : driverRatingMap.entrySet()) {
                Integer key = entry.getKey();
                Float value = entry.getValue();
                double weigh_value = value;
                weightRatingMap.put(key, weigh_value);
            }
            Map<Integer, Double> combinedWeightsMap = new HashMap<>();
            for (Map.Entry<Integer, Double> entry : weightDistanceMap.entrySet()) {
                Integer key = entry.getKey();
                Double distanceWeight = entry.getValue();
                Double ratingWeight = weightRatingMap.get(key);
                if (ratingWeight != null) {
                    double combineWeigh = distanceWeight + ratingWeight;
                    combinedWeightsMap.put(key, combineWeigh);
                }
            }
            double maxValue = Double.MIN_VALUE;
            for (double value : combinedWeightsMap.values()) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            Integer maxKey = null;
            for (Map.Entry<Integer, Double> entry : combinedWeightsMap.entrySet()) {
                if (entry.getValue() == maxValue) {
                    maxKey = entry.getKey();
                    break;
                }
            }
            Account chooseaccount = new Account();
            chooseaccount.setAccount_id(maxKey);
            choosedriver.setAccount(chooseaccount);
        }
        return choosedriver;
    }
    
    public Shipment_Order getOriginAddressForchooseDriver(int order_id){
        Shipment_Order shipment_Order = new Shipment_Order();
        String SQL_querry = "SELECT \n"
                + "    Pick_Up_Apartment_Number,\n"
                + "    Pick_Up_Street_Name,\n"
                + "    Pick_Up_District,\n"
                + "    Pick_Up_Ward,\n"
                + "    Pick_Up_City\n"
                + "FROM \n"
                + "    Shipment_Order\n"
                + "WHERE\n"
                + "	Order_ID=?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String Apartment_Number=rs.getString(1);
                String Street_Name=rs.getString(2);
                String District=rs.getString(3);
                String Ward=rs.getString(4);
                String City=rs.getString(5);
                shipment_Order.setPick_up_apartment_number(Apartment_Number);
                shipment_Order.setPick_up_street_name(Street_Name);
                shipment_Order.setPick_up_district(District);
                shipment_Order.setPick_up_ward(Ward);
                shipment_Order.setPick_up_city(City);               
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return shipment_Order;
    }
    public boolean changedriverWhenCancel(int driver_id_check,int order_id_check){
        String SQL_querry = "UPDATE Shipment_Order_Driver\n"
                + "Set Driver_ID= ?\n"
                + "Where Shipment_Order_ID = ?";
         try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, driver_id_check);
            ps.setInt(2, order_id_check);
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected!=0){
                return true;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return false;     
    }
    public Driver_type getDriverTypeWithDriver_id(int driver_id){
        Driver_type driver_type = new Driver_type();
        String SQL_querry ="SELECT Driver_Type_ID FROM Driver WHERE Driver_ID=?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int driver_type_id= rs.getInt(1);
                driver_type.setDriv_type_id(driver_type_id);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return driver_type;
    }      
    public List<ShipmentOrder_Invoice_Order_Status> getListOrderPickingUpNotYet(int driver_id,List<ShipmentOrder_Invoice_Order_Status> list) {     
       String SQL_querry_findOrder = 
                                        "SELECT " +
                                        "    so.Order_ID, " +
                                        "    so.Final_Receiver_Name, " +
                                        "    so.Final_Receiver_Phone, " +
                                        "    so.Final_Apartment_Number, " +
                                        "    so.Final_Street_Name, " +
                                        "    so.Final_District, " +
                                        "    so.Final_Ward, " +
                                        "    so.Final_City, " +
                                        "    i.Total_Amount, " +
                                        "    os.Status " +
                                        "FROM " +
                                        "    Shipment_Order so " +
                                        "JOIN " +
                                        "    Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                                        "JOIN " +
                                        "    Order_Status os ON so.Order_ID = os.Order_ID " +
                                        "JOIN " +
                                        "    Invoice i ON so.Order_ID = i.Order_ID " +
                                        "WHERE " +
                                        "    os.Status = 'Picking up' " +
                                        "    AND os.Process = 'Not yet' " +
                                        "    AND sod.Driver_ID = ?;";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findOrder);
            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                String receiver_name = rs.getString(2);
                String receiver_phone = rs.getString(3);
                String apartment_Number = rs.getString(4);
                String street_Name = rs.getString(5);
                String district = rs.getString(6);
                String ward = rs.getString(7);
                String city = rs.getString(8);
                float total_amount = rs.getFloat(9);
                String status =rs.getString(10);               
                Shipment_Order shipment_Order = new Shipment_Order();
                Invoice invoice = new Invoice();
                Order_Status order_status= new Order_Status();
                shipment_Order.setOrder_id(order_id);
                ShipmentOrder_Invoice_Order_Status ShipmentOrder_Invoice_Order_Status = new ShipmentOrder_Invoice_Order_Status();
                shipment_Order.setFinal_receiver_name(receiver_name);
                shipment_Order.setFinal_receiver_phone(receiver_phone);
                shipment_Order.setFinal_apartment_number(apartment_Number);
                shipment_Order.setFinal_street_name(street_Name);
                shipment_Order.setFinal_district(district);
                shipment_Order.setFinal_ward(ward);
                shipment_Order.setFinal_city(city);                             
                invoice.setShipment_order(shipment_Order);
                invoice.setTotal_amount(total_amount);
                order_status.setStatus(status); 
                ShipmentOrder_Invoice_Order_Status.setInvoice(invoice);
                ShipmentOrder_Invoice_Order_Status.setOrder_Status(order_status);
                list.add(ShipmentOrder_Invoice_Order_Status);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return list;
    }
    public List<ShipmentOrder_Invoice_Order_Status> getListOrderStatusOngoing(int driver_id,List<ShipmentOrder_Invoice_Order_Status> list) {     
        
        String SQL_querry_findOrder =  "SELECT " +
                                        "    so.Order_ID, " +
                                        "    so.Final_Receiver_Name, " +
                                        "    so.Final_Receiver_Phone, " +
                                        "    so.Final_Apartment_Number, " +
                                        "    so.Final_Street_Name, " +
                                        "    so.Final_District, " +
                                        "    so.Final_Ward, " +
                                        "    so.Final_City, " +
                                        "    i.Total_Amount, " +
                                        "    os.Status " +
                                        "FROM " +
                                        "    Shipment_Order so " +
                                        "JOIN " +
                                        "    Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                                        "JOIN " +
                                        "    Order_Status os ON sod.Shipment_Driver_ID = os.Shipment_Driver_ID " +
                                        "JOIN " +
                                        "    Invoice i ON so.Order_ID = i.Order_ID " +
                                        "WHERE " +
                                        "    os.Process = 'Ongoing' " +
                                        "    AND sod.Driver_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findOrder);
            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                String receiver_name = rs.getString(2);
                String receiver_phone = rs.getString(3);
                String apartment_Number = rs.getString(4);
                String street_Name = rs.getString(5);
                String district = rs.getString(6);
                String ward = rs.getString(7);
                String city = rs.getString(8);
                float total_amount = rs.getFloat(9);
                String status =rs.getString(10);               
                Shipment_Order shipment_Order = new Shipment_Order();
                Invoice invoice = new Invoice();
                Order_Status order_status= new Order_Status();
                shipment_Order.setOrder_id(order_id);
                ShipmentOrder_Invoice_Order_Status ShipmentOrder_Invoice_Order_Status = new ShipmentOrder_Invoice_Order_Status();
                shipment_Order.setFinal_receiver_name(receiver_name);
                shipment_Order.setFinal_receiver_phone(receiver_phone);
                shipment_Order.setFinal_apartment_number(apartment_Number);
                shipment_Order.setFinal_street_name(street_Name);
                shipment_Order.setFinal_district(district);
                shipment_Order.setFinal_ward(ward);
                shipment_Order.setFinal_city(city);                             
                invoice.setShipment_order(shipment_Order);
                invoice.setTotal_amount(total_amount);
                order_status.setStatus(status); 
                ShipmentOrder_Invoice_Order_Status.setInvoice(invoice);
                ShipmentOrder_Invoice_Order_Status.setOrder_Status(order_status);
                list.add(ShipmentOrder_Invoice_Order_Status);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return list;
    }
    public List<ShipmentOrder_Invoice_Order_Status> getListOrderStatusIntransit2Ongoing(int driver_id,List<ShipmentOrder_Invoice_Order_Status> list) {     
        String SQL_querry_findOrder =  "SELECT " +
                                        "    so.Order_ID, " +
                                        "    so.Final_Receiver_Name, " +
                                        "    so.Final_Receiver_Phone, " +
                                        "    so.Final_Apartment_Number, " +
                                        "    so.Final_Street_Name, " +
                                        "    so.Final_District, " +
                                        "    so.Final_Ward, " +
                                        "    so.Final_City, " +
                                        "    i.Total_Amount, " +
                                        "    os.Status " +
                                        "FROM " +
                                        "    Shipment_Order so " +
                                        "JOIN " +
                                        "    Shipment_Order_Driver sod ON so.Order_ID = sod.Shipment_Order_ID " +
                                        "JOIN " +
                                        "    Order_Status os ON sod.Shipment_Driver_ID = os.Shipment_Driver_ID " +
                                        "JOIN " +
                                        "    Invoice i ON so.Order_ID = i.Order_ID " +
                                        "WHERE " +
                                        "    os.Status = 'In Transit-2' " +
                                        "    AND os.Process = 'Ongoing' " +
                                        "    AND sod.Driver_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry_findOrder);
            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                String receiver_name = rs.getString(2);
                String receiver_phone = rs.getString(3);
                String apartment_Number = rs.getString(4);
                String street_Name = rs.getString(5);
                String district = rs.getString(6);
                String ward = rs.getString(7);
                String city = rs.getString(8);
                float total_amount = rs.getFloat(9);
                String status =rs.getString(10);               
                Shipment_Order shipment_Order = new Shipment_Order();
                Invoice invoice = new Invoice();
                Order_Status order_status= new Order_Status();
                shipment_Order.setOrder_id(order_id);
                ShipmentOrder_Invoice_Order_Status ShipmentOrder_Invoice_Order_Status = new ShipmentOrder_Invoice_Order_Status();
                shipment_Order.setFinal_receiver_name(receiver_name);
                shipment_Order.setFinal_receiver_phone(receiver_phone);
                shipment_Order.setFinal_apartment_number(apartment_Number);
                shipment_Order.setFinal_street_name(street_Name);
                shipment_Order.setFinal_district(district);
                shipment_Order.setFinal_ward(ward);
                shipment_Order.setFinal_city(city);                             
                invoice.setShipment_order(shipment_Order);
                invoice.setTotal_amount(total_amount);
                order_status.setStatus(status); 
                ShipmentOrder_Invoice_Order_Status.setInvoice(invoice);
                ShipmentOrder_Invoice_Order_Status.setOrder_Status(order_status);
                list.add(ShipmentOrder_Invoice_Order_Status);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return list;
    }
    public Shipment_Order getAddress(int Order_id) {
        String SQL_querry = "SELECT Pick_Up_Apartment_Number ,Pick_Up_Street_Name ,Pick_Up_District ,Pick_Up_Ward ,Pick_Up_City ,Final_Apartment_Number,Final_Street_Name, Final_District, Final_Ward, Final_City FROM Shipment_Order WHERE Order_ID =?";
        Shipment_Order shipment_Order = new Shipment_Order();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, Order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pick_Up_apartment_Number = rs.getString(1);
                String pick_Up_street_Name = rs.getString(2);
                String pick_Up_district = rs.getString(3);
                String pick_Up_ward = rs.getString(4);
                String pick_Up_city = rs.getString(5);
                String final_apartment_Number = rs.getString(6);
                String final_street_Name = rs.getString(7);
                String final_district = rs.getString(8);
                String final_ward = rs.getString(9);
                String final_city = rs.getString(10);
                shipment_Order.setFinal_apartment_number(final_apartment_Number);
                shipment_Order.setFinal_street_name(final_street_Name);
                shipment_Order.setFinal_district(final_district);
                shipment_Order.setFinal_ward(final_ward);
                shipment_Order.setFinal_city(final_city);
                shipment_Order.setPick_up_apartment_number(pick_Up_apartment_Number);
                shipment_Order.setPick_up_street_name(pick_Up_street_Name);
                shipment_Order.setPick_up_district(pick_Up_district);
                shipment_Order.setPick_up_ward(pick_Up_ward);
                shipment_Order.setPick_up_city(pick_Up_city);
            }
            conn.close();
        } catch (Exception e) {
        }
        return shipment_Order;
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

    public Post_Office getPostOfficeInTransit(int order_id_check) {
        Post_Office post_Office = new Post_Office();
        String SQL_querry =  "SELECT os.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City " +
                                "FROM Order_Status os " +
                                "JOIN Post_Office po ON os.Post_Office_ID = po.Post_Office_ID " +
                                "WHERE os.Status = 'In transit' AND os.Order_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, order_id_check);
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
    public Post_Office getPostOfficeAtwarehouse2(int order_id_check) {
        Post_Office post_Office = new Post_Office();
        String SQL_querry =  "SELECT os.Post_Office_ID, po.Apartment_Number, po.Street_Name, po.District, po.Ward, po.City " +
                                "FROM Order_Status os " +
                                "JOIN Post_Office po ON os.Post_Office_ID = po.Post_Office_ID " +
                                "WHERE os.Status = 'At warehouse-2' AND os.Order_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, order_id_check);
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
    public Post_Office getPostOfficeFromStatus(int order_id_check) {
        Post_Office post_Office = new Post_Office();
        int post_Office_ID = 0;
        String SQL_querry1 = "SELECT Post_Office_ID FROM Order_Status WHERE Order_ID=? AND Process='Ongoing'";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, order_id_check);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                post_Office_ID = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
        }
        String SQL_querry2 = "SELECT Post_Office_ID,Apartment_Number,Street_Name,District,Ward,City FROM Post_Office where Post_Office_ID=?";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, post_Office_ID);
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

    public Driver findDriverLocation(int driver_id) {
        String SQL_querry = "SELECT Latitude,Longitude FROM Driver WHERE Driver_ID=?";
        Driver driver = new Driver();
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                float latitude = rs.getFloat(1);
                float longitude = rs.getFloat(2);
                driver.setLatitude(latitude);
                driver.setLongitude(longitude);
            }
            conn.close();
        } catch (Exception e) {
        }
        return driver;
    }

    public boolean checkInProvince(int order_id) {
        String sqlQuerry = "SELECT In_Province FROM Shipment_Order WHERE Order_ID=?";
        int in_province = 0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuerry);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                in_province = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        if (in_province == 1) {
            return true;
        }
        return false;
    }

    public Order_Status getOrderStatus(int Check_order_id) {
        Order_Status order_status = new Order_Status();
        String SQL_querry = "SELECT Order_ID,Status,Process FROM Order_Status WHERE Order_ID=? AND Process='Ongoing'";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, Check_order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int order_id = rs.getInt(1);
                String Status = rs.getString(2);
                String process = rs.getString(3);
                Shipment_Order shipment_order = new Shipment_Order();
                shipment_order.setOrder_id(order_id);
                order_status.setShipment_order(shipment_order);
                order_status.setStatus(Status);
                order_status.setProcess(process);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        return order_status;
    }
    
    public Order_Status getOrderStatusReceiveOrder(int Check_order_id) {
        Order_Status order_status = new Order_Status();
        String SQL_querry = "SELECT Order_ID,Status,Process FROM Order_Status WHERE Order_ID=? AND Process='Not yet' AND Status='Picking up'";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, Check_order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int order_id = rs.getInt(1);
                String Status = rs.getString(2);
                String process = rs.getString(3);
                Shipment_Order shipment_order = new Shipment_Order();
                shipment_order.setOrder_id(order_id);
                order_status.setShipment_order(shipment_order);
                order_status.setStatus(Status);
                order_status.setProcess(process);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        return order_status;
    }

    public Invoice getOrderShipmentInformation(int Check_order_id) {
        Shipment_Order shipment_Order = new Shipment_Order();
        Invoice invoice = new Invoice();
        String SQL_querry =  "SELECT " +
                                "    so.Order_ID, " +
                                "    so.Final_Receiver_Name, " +
                                "    so.Final_Receiver_Phone, " +
                                "    so.Customer_ID, " +
                                "    i.Total_Amount " +
                                "FROM " +
                                "    Shipment_Order so " +
                                "JOIN " +
                                "    Invoice i ON so.Order_ID = i.Order_ID " +
                                "WHERE " +
                                "    so.Order_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, Check_order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int order_id = rs.getInt(1);
                String final_Receiver_Name = rs.getString(2);
                String final_Receiver_Phone = rs.getString(3);
                int cus_id = rs.getInt(4);
                float total_amount = rs.getFloat(5);
                shipment_Order.setFinal_receiver_name(final_Receiver_Name);
                shipment_Order.setFinal_receiver_phone(final_Receiver_Phone);
                shipment_Order.setOrder_id(order_id);
                Account account = new Account();
                account.setAccount_id(cus_id);
                Customer customer = new Customer();
                customer.setAccount(account);
                shipment_Order.setCustomer(customer);
                invoice.setTotal_amount(total_amount);
                invoice.setShipment_order(shipment_Order);

            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }
        return invoice;
    }

    public Package_Detail getPackage_DetailInformation(int Check_order_id) {
        Package_Detail package_Detail = new Package_Detail();
            String SQL_querry = "SELECT " +
                                "    ps.Package_Size_Value, " +
                                "    pt.Package_Type_Value, " +
                                "    pd.Package_Weight " +
                                "FROM " +
                                "    Package_Details pd " +
                                "JOIN " +
                                "    Package_Size ps ON pd.Package_Size_ID = ps.Package_Size_ID " +
                                "JOIN " +
                                "    Package_Type pt ON pd.Package_Type_ID = pt.Package_Type_ID " +
                                "WHERE " +
                                "    pd.Order_ID = ?;";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry);
            ps.setInt(1, Check_order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String package_Size_value = rs.getString(1);
                String package_Type_value = rs.getString(2);
                float package_Weight = rs.getFloat(3);
                Package_Size package_Size = new Package_Size();
                Package_Type package_Type = new Package_Type();
                //đưa dữ liệu vào package_Size
                package_Size.setPackage_size_value(package_Size_value);
                //đưa dữ liệu vào package_Type
                package_Type.setPackage_type_value(package_Type_value);
                //đưa dữ liệu vào package_Detail
                package_Detail.setPackage_type(package_Type);
                package_Detail.setPackage_size(package_Size);
                package_Detail.setPackage_weight(package_Weight);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        }

        return package_Detail;
    }

    public boolean updateOrderStatusAcceptOrder(int order_id,int driver_id){
        String SQL_querry1 =  "SELECT " +
                                "    Shipment_Driver_ID " +
                                "FROM " +
                                "    Shipment_Order_Driver " +
                                "WHERE " +
                                "    Driver_ID = ? AND Shipment_Order_ID = ?;";
        String SQL_querry2 =  "UPDATE Order_Status " +
                                "SET Process = 'Ongoing', " +
                                "    Shipment_Driver_ID = ?, " +
                                "    Start_Time = CURRENT_TIMESTAMP " +
                                "WHERE Order_ID = ? " +
                                "    AND Status = 'Picking up' " +
                                "    AND Process = 'Not yet';";
        int shipment_Driver_ID=0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, driver_id);
            ps.setInt(2, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              shipment_Driver_ID=rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, shipment_Driver_ID);
            ps.setInt(2, order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
    public boolean updateOrderStatusSenderInProvince(int Order_id,int driver_id) {
        String SQL_querry1 = 
                            "SELECT " +
                            "    Shipment_Driver_ID " +
                            "FROM " +
                            "    Shipment_Order_Driver " +
                            "WHERE " +
                            "    Driver_ID = ? AND Shipment_Order_ID = ?";

        String SQL_querry2 = 
                            "UPDATE Order_Status " +
                            "SET Process = 'Done', " +
                            "    End_Time = CURRENT_TIMESTAMP " +
                            "WHERE Order_ID = ? " +
                            "    AND Status = 'Picking up' " +
                            "    AND Process = 'Ongoing'";

        String SQL_querry3 = 
                            "UPDATE Order_Status " +
                            "SET Process = 'Ongoing', " +
                            "    Shipment_Driver_ID = ?, " +
                            "    Start_Time = CURRENT_TIMESTAMP " +
                            "WHERE Order_ID = ? " +
                            "    AND Status = 'Delivering' " +
                            "    AND Process = 'Not yet'";

        int shipment_Driver_ID=0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, driver_id);
            ps.setInt(2, Order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              shipment_Driver_ID=rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry3);
                    ps1.setInt(1, shipment_Driver_ID);
                    ps1.setInt(2, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean updateOrderStatusDeliveringInProvince(int Order_id,int driver_id) {       
        String SQL_querry1 = "Select \n"
                + "	Shipment_Driver_ID\n"
                + "From Shipment_Order_Driver\n"
                + "where Driver_ID=? and Shipment_Order_ID=?";
        String SQL_querry2 = "UPDATE Order_Status\n"
                + "                SET Process = 'Done',\n"
                + "                End_Time = CURRENT_TIMESTAMP\n"
                + "                WHERE Order_ID = ?\n"
                + "                AND Status = 'Delivering'\n"
                + "                AND Process = 'Ongoing'";
        String SQL_querry3 = "UPDATE Order_Status\n"
                + "SET Process = 'Done',\n"
                + "Shipment_Driver_ID=?,\n"
                + "Start_Time = CURRENT_TIMESTAMP,\n"
                + "End_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'Delivered';\n";
        int shipment_Driver_ID=0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, driver_id);
            ps.setInt(2, Order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              shipment_Driver_ID=rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);           
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry3);
                    ps1.setInt(2, shipment_Driver_ID);
                    ps1.setInt(1, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    //Thay đổi Order Status liên tỉnh
    public boolean updateOrderStatusSenderInterProvince(int Order_id,int Driver_id) {
                String SQL_querry1 = 
                                    "SELECT " +
                                    "    Shipment_Driver_ID " +
                                    "FROM " +
                                    "    Shipment_Order_Driver " +
                                    "WHERE " +
                                    "    Driver_ID = ? AND Shipment_Order_ID = ?";

               String SQL_querry2 = 
                                    "UPDATE Order_Status " +
                                    "SET Process = 'Done', " +
                                    "    End_Time = CURRENT_TIMESTAMP " +
                                    "WHERE Order_ID = ? " +
                                    "    AND Status = 'Picking up' " +
                                    "    AND Process = 'Ongoing'";

               String SQL_querry3 = 
                                    "UPDATE Order_Status " +
                                    "SET Process = 'Ongoing', " +
                                    "    Shipment_Driver_ID = ?, " +
                                    "    Start_Time = CURRENT_TIMESTAMP " +
                                    "WHERE Order_ID = ? " +
                                    "    AND Status = 'In Transit' " +
                                    "    AND Process = 'Not yet'";

        int shipment_Driver_ID=0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Driver_id);
            ps.setInt(2, Order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              shipment_Driver_ID=rs.getInt(1);
            }
        } catch (Exception ex) {
        }
        
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry3);
                    ps1.setInt(1, shipment_Driver_ID);
                    ps1.setInt(2, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean updateOrderStatusIntransitInterProvince(int Order_id) {
        String SQL_querry1 = "UPDATE Order_Status\n"
                + "SET Process = 'Done',\n"
                + "End_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'In transit'\n"
                + "AND Process = 'Ongoing';";
        String SQL_querry2 = "UPDATE Order_Status\n"
                + "SET Process = 'Ongoing',\n" 
                + "Start_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'At warehouse'\n"
                + "AND Process = 'Not yet';";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry2);
                    ps1.setInt(1, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean updateOrderStatusAtWarehouseInterProvince(int Order_id) {
// Chinh sửa sau khi merger import ticket
//        String SQL_querry1 = "UPDATE Order_Status\n"
//                + "SET Process = 'Done',\n"
//                + "End_Time = CURRENT_TIMESTAMP\n"
//                + "WHERE Order_ID = ?\n"
//                + "AND Status = 'At warehouse'\n"
//                + "AND Process = 'Ongoing';";        
        String SQL_querry2 = "UPDATE Order_Status\n"
                + "SET Process = 'Ongoing',\n"
                + "Start_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'In Transit-2'\n"
                + "AND Process = 'Not yet';";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return true;
//                try {
//                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry2);
//                    ps1.setInt(1, Order_id);                   
//                    int rowsAffected1 = ps1.executeUpdate();
//                    if (rowsAffected1 != 0) {
//                        return true;
//                    }
//                    conn.close();
//                } catch (Exception ex) {
//                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean updateOrderStatusInTransit2InterProvince(int Order_id) {
        String SQL_querry1 = "UPDATE Order_Status\n"
                + "SET Process = 'Done',\n"
                + "End_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'In transit-2'\n"
                + "AND Process = 'Ongoing';";
        String SQL_querry2 = "UPDATE Order_Status\n"
                + "SET Process = 'Ongoing',\n" // chỉnh thành Ongoing sau khi merge code
                + "Start_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'At warehouse-2'\n"
                + "AND Process = 'Not yet';";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry2);
                    ps1.setInt(1, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }
    
    public boolean updateOrderStatusAtWarehouse2InterProvince(int Order_id) {
//        String SQL_querry1 = "UPDATE Order_Status\n"
//                + "SET Process = 'Done',\n"
//                + "End_Time = CURRENT_TIMESTAMP\n"
//                + "WHERE Order_ID = ?\n"
//                + "AND Status = 'At warehouse-2'\n"
//                + "AND Process = 'Ongoing';";        
        String SQL_querry2 = "UPDATE Order_Status\n"
                + "SET Process = 'Ongoing',\n"
                + "Start_Time = CURRENT_TIMESTAMP\n"
                + "WHERE Order_ID = ?\n"
                + "AND Status = 'Delivering'\n"
                + "AND Process = 'Not yet';";
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                return true;
//                try {
//                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry2);
//                    ps1.setInt(1, Order_id);                   
//                    int rowsAffected1 = ps1.executeUpdate();
//                    if (rowsAffected1 != 0) {
//                        return true;
//                    }
//                    conn.close();
//                } catch (Exception ex) {
//                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean updateOrderStatusDeliveringInterProvince(int Order_id) {
        String SQL_querry1 = 
                            "UPDATE Order_Status " +
                            "SET Process = 'Done', " +
                            "    End_Time = CURRENT_TIMESTAMP " +
                            "WHERE Order_ID = ? " +
                            "    AND Status = 'Delivering' " +
                            "    AND Process = 'Ongoing'";

        String SQL_querry2 = 
                            "UPDATE Order_Status " +
                            "SET Process = 'Done', " +
                            "    End_Time = CURRENT_TIMESTAMP " +
                            "WHERE Order_ID = ? " +
                            "    AND Status = 'Delivered';";

        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Order_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 0) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement(SQL_querry2);
                    ps1.setInt(1, Order_id);
                    int rowsAffected1 = ps1.executeUpdate();
                    if (rowsAffected1 != 0) {
                        return true;
                    }
                    conn.close();
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    //Thay đổi Driver khí cập nhật trạng thái post_office
    public boolean changeDriverFromPostOffice(int Post_Office_id, int order_id) {
        String SQL_querry1 = 
            "SELECT Driver_ID " +
            "FROM Driver " +
            "WHERE Post_Office_ID = ? AND Driver_Type_ID = 3";

        String SQL_querry2 = 
            "INSERT INTO Shipment_Order_Driver (Shipment_Order_ID, Driver_ID) " +
            "VALUES (?, ?)";

        String SQL_querry3 = 
            "UPDATE Order_Status " +
            "SET Shipment_Driver_ID = ? " +
            "WHERE Status ='In Transit-2' AND Order_ID = ?";

        

        int driver_id = 0;
        int Shipment_Order_Driver_id=0;
        //Lấy Driver_id của truck
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Post_Office_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                driver_id = rs.getInt(1);
            }
            conn.close();
        } catch (Exception ex) {
        }
        //insert driver id vào bảng Shipment_Order_Driver
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order_id);
            ps.setInt(2, driver_id);           
            int rowsAffected = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();           
            if (rowsAffected != 0) {
                if(rs.next()){
                    Shipment_Order_Driver_id=rs.getInt(1);
                }              
            } 
            conn.close();
        } catch (Exception ex) {
        }
        //set lại Order_status
        if (Shipment_Order_Driver_id == 0) {
            return false;
        } else {            
            try {
                Connection conn = new DBcontext().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_querry3);
                ps.setInt(1, Shipment_Order_Driver_id);
                ps.setInt(2, order_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 0) {                   
                    return true;
                }
            } catch (Exception ex) {
            }           
        }                  
        return false;
    }

    public boolean changeDriverFromFinalPostOffice(int Post_Office_id, int order_id) {
        List<Driver> drivers = new ArrayList<>();
        String SQL_querry1 = 
            "SELECT Driver_ID " +
            "FROM Driver " +
            "WHERE Post_Office_ID = ? AND Driver_Type_ID = 2";

        String SQL_querry2 = 
            "INSERT INTO Shipment_Order_Driver (Shipment_Order_ID, Driver_ID) " +
            "VALUES (?, ?)";
        
        String SQL_querry3 = 
            "UPDATE Order_Status " +
            "SET Shipment_Driver_ID = ? " +
            "WHERE Status ='Delivering' AND Order_ID = ?";
        int driver_id = 0;
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry1);
            ps.setInt(1, Post_Office_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int driver_id_list = rs.getInt(1);
                Account account = new Account();
                account.setAccount_id(driver_id_list);
                Driver driver = new Driver();
                driver.setAccount(account);
                drivers.add(driver);
            }
            conn.close();
        } catch (Exception ex) {
        }
        driver_id = getRandomDriverId(drivers);
        int Shipment_Order_Driver_id=0;
        //insert driver id vào bảng Shipment_Order_Driver
        try {
            Connection conn = new DBcontext().getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_querry2,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order_id);
            ps.setInt(2, driver_id);           
            int rowsAffected = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();           
            if (rowsAffected != 0) {
                if(rs.next()){
                    Shipment_Order_Driver_id=rs.getInt(1);
                }              
            } 
            conn.close();
        } catch (Exception ex) {
        }
        
        //set lại Order_status
        if (Shipment_Order_Driver_id == 0) {
            return false;
        } else {
            try {
                Connection conn = new DBcontext().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_querry3);
                ps.setInt(1, Shipment_Order_Driver_id);
                ps.setInt(2, order_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 0) {
                    return true;
                }
            } catch (Exception ex) {
            }
        }    
        return false;
    }

    public static int getRandomDriverId(List<Driver> drivers) {
        Random random = new Random();
        int index = random.nextInt(drivers.size());
        return drivers.get(index).getAccount().getAccount_id();
    }

    public void insertShipmentOrderDriver(Shipment_Order shipmentOrder, Driver driver) {
        String sqlQuery = "INSERT INTO Shipment_Order_Driver (Shipment_Order_ID, Driver_ID) VALUES (?, ?)";

        try (Connection conn = new DBcontext().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            // Set the values for the PreparedStatement
            ps.setInt(1, shipmentOrder.getOrder_id());
            ps.setInt(2, driver.getAccount().getAccount_id());

            // Execute the insert statement
            ps.executeUpdate();
            System.out.println("Inserted Shipment_Order_Driver record successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
