/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class ShipmentOrder_Invoice_Order_Status {
    private Invoice invoice;
    private Order_Status order_Status;

    public ShipmentOrder_Invoice_Order_Status() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Order_Status getOrder_Status() {
        return order_Status;
    }

    public void setOrder_Status(Order_Status order_Status) {
        this.order_Status = order_Status;
    }

    @Override
    public String toString() {
        return "ShipmentOrder_Invoice_OrderStatus{" + "invoice=" + invoice + ", order_Status=" + order_Status + '}';
    }
    
    
}
