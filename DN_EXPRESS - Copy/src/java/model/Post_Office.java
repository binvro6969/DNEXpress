/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Post_Office {
    private int post_office_id;
    private String apartmentNumber;
    private String streetName;
    private String district;
    private String ward;
    private String city;

    public Post_Office() {
    }
    
    public Post_Office(String apartmentNumber, String streetName, String district, String ward, String city) {
        this.apartmentNumber = apartmentNumber;
        this.streetName = streetName;
        this.district = district;
        this.ward = ward;
        this.city = city;
    }
    
    public Post_Office(int post_office_id, String apartmentNumber, String streetName, String district, String ward, String city) {
        this.post_office_id = post_office_id;
        this.apartmentNumber = apartmentNumber;
        this.streetName = streetName;
        this.district = district;
        this.ward = ward;
        this.city = city;
    }

    public int getPost_office_id() {
        return post_office_id;
    }

    public void setPost_office_id(int post_office_id) {
        this.post_office_id = post_office_id;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Post_Office{" + "post_office_id=" + post_office_id + ", apartmentNumber=" + apartmentNumber + ", streetName=" + streetName + ", district=" + district + ", ward=" + ward + ", city=" + city + '}';
    }   
}
