/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Promotion {
    private int promotion_id;
    private String promotion_value;
    private String promotion_name;

    public Promotion(int promotion_id, String promotion_value, String promotion_name) {
        this.promotion_id = promotion_id;
        this.promotion_value = promotion_value;
        this.promotion_name = promotion_name;
    }

    public int getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
    }
 
    public String getPromotion_value() {
        return promotion_value;
    }

    public void setPromotion_value(String promotion_value) {
        this.promotion_value = promotion_value;
    }

    public String getPromotion_name() {
        return promotion_name;
    }

    public void setPromotion_name(String promotion_name) {
        this.promotion_name = promotion_name;
    }    
}
