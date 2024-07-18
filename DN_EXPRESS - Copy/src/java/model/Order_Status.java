/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haian
 */
public class Order_Status {
    private int order_status_id;
    private Shipment_Order shipment_order;
    private String location;
    private String location_type;
    private String status;
    private Post_Office post_office;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String process;
    private Shipment_Order_Driver shipment_Order_driver;

    public Order_Status() {
    }

    public Order_Status(int order_status_id, Shipment_Order shipment_order, String location, String location_type, String status, Post_Office post_office, LocalDateTime start_time, LocalDateTime end_time, String process) {
        this.order_status_id = order_status_id;
        this.shipment_order = shipment_order;
        this.location = location;
        this.location_type = location_type;
        this.status = status;
        this.post_office = post_office;
        this.start_time = start_time;
        this.end_time = end_time;
        this.process = process;
    }

    public Order_Status(int order_status_id, Shipment_Order shipment_order, String location, String location_type, String status, Post_Office post_office, LocalDateTime start_time, LocalDateTime end_time, String process, Shipment_Order_Driver shipment_Order_driver) {
        this.order_status_id = order_status_id;
        this.shipment_order = shipment_order;
        this.location = location;
        this.location_type = location_type;
        this.status = status;
        this.post_office = post_office;
        this.start_time = start_time;
        this.end_time = end_time;
        this.process = process;
        this.shipment_Order_driver = shipment_Order_driver;
    }

    public Shipment_Order_Driver getShipment_Order_driver() {
        return shipment_Order_driver;
    }

    public void setShipment_Order_driver(Shipment_Order_Driver shipment_Order_driver) {
        this.shipment_Order_driver = shipment_Order_driver;
    }
    
    
    
    
   
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
    
    public int getOrder_status_id() {
        return order_status_id;
    }

    public void setOrder_status_id(int order_status_id) {
        this.order_status_id = order_status_id;
    }

    public Shipment_Order getShipment_order() {
        return shipment_order;
    }

    public void setShipment_order(Shipment_Order shipment_order) {
        this.shipment_order = shipment_order;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Post_Office getPost_office() {
        return post_office;
    }

    public void setPost_office(Post_Office post_office) {
        this.post_office = post_office;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        try {
            this.start_time = LocalDateTime.parse(start_time, formatter);
        } catch (Exception ex) {
            Logger.getLogger(Order_Status.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        try {
            this.end_time = LocalDateTime.parse(end_time, formatter);
        } catch (Exception ex) {
            Logger.getLogger(Order_Status.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getStartDate() {
        return start_time != null ? start_time.toLocalDate().toString() : null;
    }

    public String getEndDate() {
        return end_time != null ? end_time.toLocalDate().toString() : null;
    }

    public String getStartTime() {
        return start_time != null ? start_time.toLocalTime().toString() : null;
    }

    public String getEndTime() {
        return end_time != null ? end_time.toLocalTime().toString() : null;
    }

    @Override
    public String toString() {
        return "Order_Status{" + "order_status_id=" + order_status_id + ", shipment_order=" + shipment_order + ", location=" + location + ", location_type=" + location_type + ", status=" + status + ", post_office=" + post_office + ", start_time=" + start_time + ", end_time=" + end_time + ", process=" + process + ", shipment_Order_driver=" + shipment_Order_driver + '}';
    }
    
  
}
