/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Shipment_Order_Driver {
    private int Shipment_Driver_ID;
    private Shipment_Order shipment_Order;
    private Driver driver;

    public Shipment_Order_Driver() {
    }

    public Shipment_Order_Driver(int Shipment_Driver_ID, Shipment_Order shipment_Order, Driver driver) {
        this.Shipment_Driver_ID = Shipment_Driver_ID;
        this.shipment_Order = shipment_Order;
        this.driver = driver;
    }
    
    
    public int getShipment_Driver_ID() {
        return Shipment_Driver_ID;
    }

    public void setShipment_Driver_ID(int Shipment_Driver_ID) {
        this.Shipment_Driver_ID = Shipment_Driver_ID;
    }

    public Shipment_Order getShipment_Order() {
        return shipment_Order;
    }

    public void setShipment_Order(Shipment_Order shipment_Order) {
        this.shipment_Order = shipment_Order;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Shipment_Order_Driver{" + "Shipment_Driver_ID=" + Shipment_Driver_ID + ", shipment_Order=" + shipment_Order + ", driver=" + driver + '}';
    }     
}
