/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author haian
 */
public class Import_Export_Ticket {
    private int import_export_ticket_id;
    private Post_Office post_office;
    private Staff staff;
    private Manager manager;
    private Package_Detail package_detail;
    private Ticket_Status ticket_status;
    private Date create_date;
    private Date censorial_date;
    private String note;
    private String integrity;
    private String labeling;
    private String quantity;
    private String documentation;
    private String[] checkboxStatus ;
    private String ticketType;
    

    public Import_Export_Ticket() {
    }

    public Import_Export_Ticket(int import_export_ticket_id, Post_Office post_office, Staff staff, Manager manager, Package_Detail package_detail, Ticket_Status ticket_status, Date create_date, Date censorial_date, String note, String integrity, String labeling, String quantity, String documentation, String[] checkboxStatus, String ticketType) {
        this.import_export_ticket_id = import_export_ticket_id;
        this.post_office = post_office;
        this.staff = staff;
        this.manager = manager;
        this.package_detail = package_detail;
        this.ticket_status = ticket_status;
        this.create_date = create_date;
        this.censorial_date = censorial_date;
        this.note = note;
        this.integrity = integrity;
        this.labeling = labeling;
        this.quantity = quantity;
        this.documentation = documentation;
        this.checkboxStatus = checkboxStatus;
        this.ticketType = ticketType;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    

    public String[] getCheckboxStatus() {
        return checkboxStatus;
    }

    public void setCheckboxStatus(String[] checkboxStatus) {
        this.checkboxStatus = checkboxStatus;
    }

   

   

  

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }

    public String getLabeling() {
        return labeling;
    }

    public void setLabeling(String labeling) {
        this.labeling = labeling;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    

    public Post_Office getPost_office() {
        return post_office;
    }

    public void setPost_office(Post_Office post_office) {
        this.post_office = post_office;
    }
    
    public int getImport_export_ticket_id() {
        return import_export_ticket_id;
    }
    
    public void setImport_export_ticket_id(int import_export_ticket_id) {
        this.import_export_ticket_id = import_export_ticket_id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Package_Detail getPackage_detail() {
        return package_detail;
    }

    public void setPackage_detail(Package_Detail package_detail) {
        this.package_detail = package_detail;
    }

    public Ticket_Status getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(Ticket_Status ticket_status) {
        this.ticket_status = ticket_status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getCensorial_date() {
        return censorial_date;
    }

    public void setCensorial_date(Date censorial_date) {
        this.censorial_date = censorial_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Import_Export_Ticket{" + "import_export_ticket_id=" + import_export_ticket_id + ", post_office=" + post_office + ", staff=" + staff + ", manager=" + manager + ", package_detail=" + package_detail + ", ticket_status=" + ticket_status + ", create_date=" + create_date + ", censorial_date=" + censorial_date + ", note=" + note + ", integrity=" + integrity + ", labeling=" + labeling + ", quantity=" + quantity + ", documentation=" + documentation + ", checkboxStatus=" + checkboxStatus + ", ticketType=" + ticketType + '}';
    }



}
