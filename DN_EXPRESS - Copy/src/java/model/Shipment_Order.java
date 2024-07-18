/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haian
 */
public class Shipment_Order {

    private int order_id;
    private Customer customer;
    private LocalDateTime created_date;
    private LocalDateTime order_date;
    private float cust_driver_rating;
    private String cust_feedback;
    private String pick_up_apartment_number;
    private String pick_up_street_name;
    private String pick_up_district;
    private String pick_up_ward;
    private String pick_up_city;
    private String final_receiver_name;
    private String final_receiver_phone;
    private String final_apartment_number;
    private String final_street_name;
    private String final_district;
    private String final_ward;
    private String final_city;
    private boolean in_province;

    public Shipment_Order() {
    }

    public Shipment_Order(int order_id, Customer customer, LocalDateTime created_date, LocalDateTime order_date, float cust_driver_rating, String cust_feedback, String pick_up_apartment_number, String pick_up_street_name, String pick_up_district, String pick_up_ward, String pick_up_city, String final_receiver_name, String final_receiver_phone, String final_apartment_number, String final_street_name, String final_district, String final_ward, String final_city, boolean in_province) {
        this.order_id = order_id;
        this.customer = customer;
        this.created_date = created_date;
        this.order_date = order_date;
        this.cust_driver_rating = cust_driver_rating;
        this.cust_feedback = cust_feedback;
        this.pick_up_apartment_number = pick_up_apartment_number;
        this.pick_up_street_name = pick_up_street_name;
        this.pick_up_district = pick_up_district;
        this.pick_up_ward = pick_up_ward;
        this.pick_up_city = pick_up_city;
        this.final_receiver_name = final_receiver_name;
        this.final_receiver_phone = final_receiver_phone;
        this.final_apartment_number = final_apartment_number;
        this.final_street_name = final_street_name;
        this.final_district = final_district;
        this.final_ward = final_ward;
        this.final_city = final_city;
        this.in_province = in_province;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        if (created_date.length() > 16) {
            created_date = created_date.substring(0, 16);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.created_date = LocalDateTime.parse(created_date, formatter);
        } catch (Exception ex) {
            Logger.getLogger(Shipment_Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    
    public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return created_date != null ? created_date.format(formatter) : "";
    }
    
    public void setOrder_date(String order_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Adjust the pattern to match your date-time format
        try {
            this.order_date = LocalDateTime.parse(order_date, formatter);
        } catch (DateTimeParseException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, "Failed to parse dob: " + order_date, ex);
        }
    }


    public float getCust_driver_rating() {
        return cust_driver_rating;
    }

    public void setCust_driver_rating(float cust_driver_rating) {
        this.cust_driver_rating = cust_driver_rating;
    }

    public String getCust_feedback() {
        return cust_feedback;
    }

    public void setCust_feedback(String cust_feedback) {
        this.cust_feedback = cust_feedback;
    }

    public String getPick_up_apartment_number() {
        return pick_up_apartment_number;
    }

    public void setPick_up_apartment_number(String pick_up_apartment_number) {
        this.pick_up_apartment_number = pick_up_apartment_number;
    }

    public String getPick_up_street_name() {
        return pick_up_street_name;
    }

    public void setPick_up_street_name(String pick_up_street_name) {
        this.pick_up_street_name = pick_up_street_name;
    }

    public String getPick_up_district() {
        return pick_up_district;
    }

    public void setPick_up_district(String pick_up_district) {
        this.pick_up_district = pick_up_district;
    }

    public String getPick_up_ward() {
        return pick_up_ward;
    }

    public void setPick_up_ward(String pick_up_ward) {
        this.pick_up_ward = pick_up_ward;
    }

    public String getPick_up_city() {
        return pick_up_city;
    }

    public void setPick_up_city(String pick_up_city) {
        this.pick_up_city = pick_up_city;
    }

    public String getFinal_receiver_name() {
        return final_receiver_name;
    }

    public void setFinal_receiver_name(String final_receiver_name) {
        this.final_receiver_name = final_receiver_name;
    }

    public String getFinal_receiver_phone() {
        return final_receiver_phone;
    }

    public void setFinal_receiver_phone(String final_receiver_phone) {
        this.final_receiver_phone = final_receiver_phone;
    }

    public String getFinal_apartment_number() {
        return final_apartment_number;
    }

    public void setFinal_apartment_number(String final_apartment_number) {
        this.final_apartment_number = final_apartment_number;
    }

    public String getFinal_street_name() {
        return final_street_name;
    }

    public void setFinal_street_name(String final_street_name) {
        this.final_street_name = final_street_name;
    }

    public String getFinal_district() {
        return final_district;
    }

    public void setFinal_district(String final_district) {
        this.final_district = final_district;
    }

    public String getFinal_ward() {
        return final_ward;
    }

    public void setFinal_ward(String final_ward) {
        this.final_ward = final_ward;
    }

    public String getFinal_city() {
        return final_city;
    }

    public void setFinal_city(String final_city) {
        this.final_city = final_city;
    }

    public boolean isIn_province() {
        return in_province;
    }

    public void setIn_province(boolean in_province) {
        this.in_province = in_province;
    }

    @Override
    public String toString() {
        return "Shipment_Order{" + "order_id=" + order_id + ", customer=" + customer + ", order_date=" + order_date + ", cust_driver_rating=" + cust_driver_rating + ", cust_feedback=" + cust_feedback + ", pick_up_apartment_number=" + pick_up_apartment_number + ", pick_up_street_name=" + pick_up_street_name + ", pick_up_district=" + pick_up_district + ", pick_up_ward=" + pick_up_ward + ", pick_up_city=" + pick_up_city + ", final_receiver_name=" + final_receiver_name + ", final_receiver_phone=" + final_receiver_phone + ", final_apartment_number=" + final_apartment_number + ", final_street_name=" + final_street_name + ", final_district=" + final_district + ", final_ward=" + final_ward + ", final_city=" + final_city + ", in_province=" + in_province + '}';
    }

}
