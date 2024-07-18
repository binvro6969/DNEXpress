/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author haian
 */
public class Payment_Method {
    private int payment_method_id;
    private String payment_method_value;

    public Payment_Method(int payment_method_id, String payment_method_value) {
        this.payment_method_id = payment_method_id;
        this.payment_method_value = payment_method_value;
    }
    public Payment_Method(){
        
    }

    public int getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPayment_method_value() {
        return payment_method_value;
    }

    public void setPayment_method_value(String payment_method_value) {
        this.payment_method_value = payment_method_value;
    }        
}
 