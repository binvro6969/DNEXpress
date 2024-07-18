/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Driver_type {
    private int  driv_type_id;
    private String driv_type_value;

    public Driver_type() {
    }
    
    
    public Driver_type(int driv_type_id, String driv_type_value) {
        this.driv_type_id = driv_type_id;
        this.driv_type_value = driv_type_value;
    }

    public int getDriv_type_id() {
        return driv_type_id;
    }

    public void setDriv_type_id(int driv_type_id) {
        this.driv_type_id = driv_type_id;
    }

    public String getDriv_type_value() {
        return driv_type_value;
    }

    public void setDriv_type_value(String driv_type_value) {
        this.driv_type_value = driv_type_value;
    }

    public Driver_type(int driv_type_id) {
        this.driv_type_id = driv_type_id;
    }

    @Override
    public String toString() {
        return "Driver_type{" + "driv_type_id=" + driv_type_id + ", driv_type_value=" + driv_type_value + '}';
    }
    
}
