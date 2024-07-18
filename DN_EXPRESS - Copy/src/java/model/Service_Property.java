/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author haian
 */
public class Service_Property  {
    private Service_Type service_type;
    private int service_property_id;
    private String service_property_value;
    private float service_property_price; 

    public Service_Property(Service_Type service_type, int service_property_id, String service_property_value, float service_property_price) {
        this.service_type = service_type;
        this.service_property_id = service_property_id;
        this.service_property_value = service_property_value;
        this.service_property_price = service_property_price;
    }

    public Service_Property(){}
    
    public int getService_property_id() {
        return service_property_id;
    }

    public void setService_property_id(int service_property_id) {
        this.service_property_id = service_property_id;
    }

  

    public Service_Type getService_type() {
        return service_type;
    }

    public void setService_type(Service_Type service_type) {
        this.service_type = service_type;
    }

    public String getService_property_value() {
        return service_property_value;
    }

    public void setService_property_value(String service_property_value) {
        this.service_property_value = service_property_value;
    }

    public float getService_property_price() {
        return service_property_price;
    }

    public void setService_property_price(float service_property_price) {
        this.service_property_price = service_property_price;
    }

    @Override
    public String toString() {
        return "Service_Property{" + "service_type=" + service_type + ", service_property_id=" + service_property_id + ", service_property_value=" + service_property_value + ", service_property_price=" + service_property_price + '}';
    }
}
