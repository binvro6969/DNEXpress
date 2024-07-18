/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author haian
 */
public class Delivery_Type{

    private int delivery_type_id;
    private String delivery_type_value;
    private float delivery_type_price;

    public Delivery_Type(int delivery_type_id, String delivery_type_value, float delivery_type_price) {
        this.delivery_type_id = delivery_type_id;
        this.delivery_type_value = delivery_type_value;
        this.delivery_type_price = delivery_type_price;
    }
    
    public Delivery_Type(int delivery_type_id, String delivery_type_value) {
        this.delivery_type_id = delivery_type_id;
        this.delivery_type_value = delivery_type_value;
    }
    
    public Delivery_Type() {

    }

    public int getDelivery_type_id() {
        return delivery_type_id;
    }

    public void setDelivery_type_id(int delivery_type_id) {
        this.delivery_type_id = delivery_type_id;
    }

    public String getDelivery_type_value() {
        return delivery_type_value;
    }

    public void setDelivery_type_value(String delivery_type_value) {
        this.delivery_type_value = delivery_type_value;
    }

    public float getDelivery_type_price() {
        return delivery_type_price;
    }

    public void setDelivery_type_price(float delivery_type_price) {
        this.delivery_type_price = delivery_type_price;
    }

    @Override
    public String toString() {
        return "Delivery_Type{" + "delivery_type_id=" + delivery_type_id + ", delivery_type_value=" + delivery_type_value + ", delivery_type_price=" + delivery_type_price + '}';
    }

    
    

}
