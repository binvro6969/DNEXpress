/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Invoice {

    private Shipment_Order shipment_order;
    private Payment_Method payment_method;
    private float service_fee;
    private float total_amount;

    public Invoice(Shipment_Order shipment_order, Payment_Method payment_method, float service_fee, float total_amount) {
        this.shipment_order = shipment_order;
        this.payment_method = payment_method;
        this.service_fee = service_fee;
        this.total_amount = total_amount;
    }

 
    public Invoice() {
    }

    public Shipment_Order getShipment_order() {
        return shipment_order;
    }

    public void setShipment_order(Shipment_Order shipment_order) {
        this.shipment_order = shipment_order;
    }

    public Payment_Method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Payment_Method payment_method) {
        this.payment_method = payment_method;
    }

    
    public float getService_fee() {
        return service_fee;
    }

    public void setService_fee(float service_fee) {
        this.service_fee = service_fee;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "Invoice{" + "shipment_order=" + shipment_order + ", payment_method=" + payment_method + ", service_fee=" + service_fee + ", total_amount=" + total_amount + '}';
    }

    
}
