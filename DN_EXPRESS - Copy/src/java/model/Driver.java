/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Driver {   
    private Account account;
    private String license_numb;
    private Driver_type driver_type;
    private boolean status;
    private float latitude;
    private float longitude;
    private float rating;
    private Post_Office post_office;
    
    

    public Driver(Account account, String license_numb, Driver_type driver_type, boolean status, float latitude, float longitude, Post_Office post_office) {       
        this.account = account;
        this.license_numb = license_numb;
        this.driver_type = driver_type;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.post_office = post_office;
    }
    
    public Driver( Account account, String license_numb, Driver_type driver_type, boolean status) {       
        this.account = account;
        this.license_numb = license_numb;
        this.driver_type = driver_type;
        this.status = status;
    }
    
    public Driver(Account account, String license_numb, Driver_type driver_type, boolean status, float latitude, float longitude, float rating, Post_Office post_office) {
        this.account = account;
        this.license_numb = license_numb;
        this.driver_type = driver_type;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.post_office = post_office;
    }

    public Driver() {
        
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
        
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLicense_numb() {
        return license_numb;
    }

    public void setLicense_numb(String license_numb) {
        this.license_numb = license_numb;
    }

    
    public Driver_type getDriver_type() {
        return driver_type;
    }

    public void setDriver_type(Driver_type driver_type) {
        this.driver_type = driver_type;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Post_Office getPost_office() {
        return post_office;
    }

    public void setPost_office(Post_Office post_office) {
        this.post_office = post_office;
    }

    public Driver(Account account, Driver_type driver_type, boolean status) {
        this.account = account;
        this.driver_type = driver_type;
        this.status = status;
    }

    public Driver(Account account) {
        this.account = account;
    }

    public Driver(Account account, String license_numb) {
        this.account = account;
        this.license_numb = license_numb;
    }

    @Override
    public String toString() {
        return "Driver{" + "account=" + account + ", license_numb=" + license_numb + ", driver_type=" + driver_type + ", status=" + status + ", latitude=" + latitude + ", longitude=" + longitude + ", rating=" + rating + ", post_office=" + post_office + '}';
    }

            
    
}
