/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Promotion_Property {
    private int promotion_property_id;
    private Promotion promotion;
    private Package_Detail package_details;

    public Promotion_Property(int promotion_property_id, Promotion promotion, Package_Detail package_details) {
        this.promotion_property_id = promotion_property_id;
        this.promotion = promotion;
        this.package_details = package_details;
    }

    public int getPromotion_property_id() {
        return promotion_property_id;
    }

    public void setPromotion_property_id(int promotion_property_id) {
        this.promotion_property_id = promotion_property_id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Package_Detail getPackage_details() {
        return package_details;
    }

    public void setPackage_details(Package_Detail package_details) {
        this.package_details = package_details;
    }

    @Override
    public String toString() {
        return "Promotion_Property{" + "promotion_property_id=" + promotion_property_id + ", promotion=" + promotion + ", package_details=" + package_details + '}';
    }
    
}
