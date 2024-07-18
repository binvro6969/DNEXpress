/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Service_Type {

    private int service_type_id;
    private String service_type_value;

    public Service_Type(int service_type_id, String service_type_value) {
        this.service_type_id = service_type_id;
        this.service_type_value = service_type_value;
    }

    public Service_Type() {

    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public String getService_type_value() {
        return service_type_value;
    }

    public void setService_type_value(String service_type_value) {
        this.service_type_value = service_type_value;
    }

  

    @Override
    public String toString() {
        return "Service_Type{" + "service_type_id=" + service_type_id + ", service_type_value=" + service_type_value + '}';
    }

}
