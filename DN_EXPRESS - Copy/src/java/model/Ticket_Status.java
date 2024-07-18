/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Ticket_Status {
    private int ticket_status_id;
    private String ticket_status_value;

    public Ticket_Status(int ticket_status_id, String ticket_status_value) {
        this.ticket_status_id = ticket_status_id;
        this.ticket_status_value = ticket_status_value;
    }
    
    public int getTicket_status_id() {
        return ticket_status_id;
    }

    public void setTicket_status_id(int ticket_status_id) {
        this.ticket_status_id = ticket_status_id;
    }

    public String getTicket_status_value() {
        return ticket_status_value;
    }

    public void setTicket_status_value(String ticket_status_value) {
        this.ticket_status_value = ticket_status_value;
    }

    @Override
    public String toString() {
        return "Ticket_Status{" + "ticket_status_id=" + ticket_status_id + ", ticket_status_value=" + ticket_status_value + '}';
    }
    
}
