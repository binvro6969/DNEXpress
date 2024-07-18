/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Issue_ticket {
    private int issue_ticketId;    
    private Customer customer;
    private Driver driver;
    private Staff staff;
    private String driver_type_value;

    public Issue_ticket() {
    }

    public Issue_ticket(int issue_ticketId, Customer customer, Driver driver, Staff staff, String driver_type_value) {
        this.issue_ticketId = issue_ticketId;
        this.customer = customer;
        this.driver = driver;
        this.staff = staff;
        this.driver_type_value = driver_type_value;
    }
    
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
      
    public int getIssue_ticketId() {
        return issue_ticketId;
    }

    public void setIssue_ticketId(int issue_ticketId) {
        this.issue_ticketId = issue_ticketId;
    }
     
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getDriver_type_value() {
        return driver_type_value;
    }

    public void setDriver_type_value(String driver_type_value) {
        this.driver_type_value = driver_type_value;
    }
    
    
}
