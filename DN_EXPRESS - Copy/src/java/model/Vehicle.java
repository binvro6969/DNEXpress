 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Vehicle {
    private Driver driver;
    private Vehicle_type vehicle_type;
    private String plate_numb;
    private String brand;
    private String image;

    public Vehicle(Driver driver, Vehicle_type vehicle_type, String plate_numb, String brand, String image) {
        this.driver = driver;
        this.vehicle_type = vehicle_type;
        this.plate_numb = plate_numb;
        this.brand = brand;
        this.image = image;
    }
    
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle_type getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(Vehicle_type vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getPlate_numb() {
        return plate_numb;
    }

    public void setPlate_numb(String plate_numb) {
        this.plate_numb = plate_numb;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "driver=" + driver + ", vehicle_type=" + vehicle_type + ", plate_numb=" + plate_numb + ", brand=" + brand + ", image=" + image + '}';
    }
    
}
