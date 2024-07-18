/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Vehicle_type {
    private int vehicle_type_id;
    private String vehicle_type_value;

    public Vehicle_type(int vehicle_type_id, String vehicle_type_value) {
        this.vehicle_type_id = vehicle_type_id;
        this.vehicle_type_value = vehicle_type_value;
    }

    public int getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(int vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }   

    public String getVehicle_type_value() {
        return vehicle_type_value;
    }

    public void setVehicle_type_value(String vehicle_type_value) {
        this.vehicle_type_value = vehicle_type_value;
    }

    @Override
    public String toString() {
        return "Vehicle_type{" + "vehicle_type_id=" + vehicle_type_id + ", vehicle_type_value=" + vehicle_type_value + '}';
    }
    
    
}
