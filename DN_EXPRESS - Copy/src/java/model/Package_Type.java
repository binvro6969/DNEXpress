/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author haian
 */
public class Package_Type {
    private int package_type_id;
    private String package_type_value;
    private float package_type_price;

    public Package_Type() {
    }

    public Package_Type(int package_type_id, String package_type_value, float package_type_price) {
        this.package_type_id = package_type_id;
        this.package_type_value = package_type_value;
        this.package_type_price = package_type_price;
    }
    
   

    public int getPackage_type_id() {
        return package_type_id;
    }

    public void setPackage_type_id(int package_type_id) {
        this.package_type_id = package_type_id;
    }

    public String getPackage_type_value() {
        return package_type_value;
    }

    public void setPackage_type_value(String package_type_value) {
        this.package_type_value = package_type_value;
    } 

    public float getPackage_type_price() {
        return package_type_price;
    }

    public void setPackage_type_price(float package_type_price) {
        this.package_type_price = package_type_price;
    }

    @Override
    public String toString() {
        return "Package_Type{" + "package_type_id=" + package_type_id + ", package_type_value=" + package_type_value + ", package_type_price=" + package_type_price + '}';
    }

    
    
}
