/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haian
 */
public class Package_Detail {

    private Shipment_Order shipment_order;
    private Package_Size package_size;
    private Package_Type package_type;
    private Delivery_Type delivery_type;
    private String package_note;
    private LocalDateTime requested_delivery_date;
    private Float package_weight;
    private double package_value;
    private String package_img;
    private LocalDateTime requested_receiving_date;
    private String package_receiving_note;

    public Package_Detail(Shipment_Order shipment_order, Package_Size package_size, Package_Type package_type, Delivery_Type delivery_type, String package_note, LocalDateTime requested_delivery_date, Float package_weight, double package_value, String package_img, LocalDateTime requested_receiving_date, String package_receiving_note) {
        this.shipment_order = shipment_order;
        this.package_size = package_size;
        this.package_type = package_type;
        this.delivery_type = delivery_type;
        this.package_note = package_note;
        this.requested_delivery_date = requested_delivery_date;
        this.package_weight = package_weight;
        this.package_value = package_value;
        this.package_img = package_img;
        this.requested_receiving_date = requested_receiving_date;
        this.package_receiving_note = package_receiving_note;
    }

    public Package_Detail() {

    }

    public Shipment_Order getShipment_order() {
        return shipment_order;
    }

    public void setShipment_order(Shipment_Order shipment_order) {
        this.shipment_order = shipment_order;
    }

    public Package_Size getPackage_size() {
        return package_size;
    }

    public void setPackage_size(Package_Size package_size) {
        this.package_size = package_size;
    }

    public Package_Type getPackage_type() {
        return package_type;
    }

    public void setPackage_type(Package_Type package_type) {
        this.package_type = package_type;
    }

    public Delivery_Type getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(Delivery_Type delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getPackage_note() {
        return package_note;
    }

    public void setPackage_note(String package_note) {
        this.package_note = package_note;
    }

    public Float getPackage_weight() {
        return package_weight;
    }

    public void setPackage_weight(Float package_weight) {
        this.package_weight = package_weight;
    }

    public double getPackage_value() {
        return package_value;
    }

    public void setPackage_value(double package_value) {
        this.package_value = package_value;
    }

    public String getPackage_img() {
        return package_img;
    }

    public void setPackage_img(String package_img) {
        this.package_img = package_img;
    }

    public LocalDateTime getRequested_delivery_date() {
        return requested_delivery_date;
    }

    public void setRequested_delivery_date(String requested_delivery_date) {
        if (requested_delivery_date.length() > 16) {
            requested_delivery_date = requested_delivery_date.substring(0, 16);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.requested_delivery_date = LocalDateTime.parse(requested_delivery_date, formatter);
        } catch (Exception ex) {
            Logger.getLogger(Package_Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalDateTime getRequested_receiving_date() {
        return requested_receiving_date;
    }

    public void setRequested_receiving_date(String requested_receiving_date) {
        if (requested_receiving_date != null) {
            if (requested_receiving_date.length() > 16) {
                requested_receiving_date = requested_receiving_date.substring(0, 16);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                this.requested_receiving_date = LocalDateTime.parse(requested_receiving_date, formatter);
            } catch (Exception ex) {
                Logger.getLogger(Package_Detail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getPackage_receiving_note() {
        return package_receiving_note;
    }

    public void setPackage_receiving_note(String package_receiving_note) {
        this.package_receiving_note = package_receiving_note;
    }

    @Override
    public String toString() {
        return "Package_Detail{" + "shipment_order=" + shipment_order + ", package_size=" + package_size + ", package_type=" + package_type + ", delivery_type=" + delivery_type + ", package_note=" + package_note + ", requested_delivery_date=" + requested_delivery_date + ", package_weight=" + package_weight + ", package_value=" + package_value + ", package_img=" + package_img + ", requested_receiving_date=" + requested_receiving_date + ", package_receiving_note=" + package_receiving_note + '}';
    }

}
