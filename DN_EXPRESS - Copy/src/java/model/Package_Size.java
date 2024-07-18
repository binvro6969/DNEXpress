/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author haian
 */
public class Package_Size  {
    private int package_size_id;
    private String package_size_value;
    private float package_size_price;

    public Package_Size() {
    }

    public Package_Size(int package_size_id, String package_size_value, float package_size_price) {
        this.package_size_id = package_size_id;
        this.package_size_value = package_size_value;
        this.package_size_price = package_size_price;
    }
    
   
    public int getPackage_size_id() {
        return package_size_id;
    }

    public void setPackage_size_id(int package_size_id) {
        this.package_size_id = package_size_id;
    }

    public String getPackage_size_value() {
        return package_size_value;
    }

    public void setPackage_size_value(String package_size_value) {
        this.package_size_value = package_size_value;
    }

    public float getPackage_size_price() {
        return package_size_price;
    }

    public void setPackage_size_price(float package_size_price) {
        this.package_size_price = package_size_price;
    }

    @Override
    public String toString() {
        return "Package_Size{" + "package_size_id=" + package_size_id + ", package_size_value=" + package_size_value + ", package_size_price=" + package_size_price + '}';
    }

    

    
}
