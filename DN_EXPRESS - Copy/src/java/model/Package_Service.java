/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Package_Service {
    private Service_Property service_property;
    private Package_Detail package_detail;

    public Package_Service(Service_Property service_property, Package_Detail package_detail) {
        this.service_property = service_property;
        this.package_detail = package_detail;
    }
    
    public Package_Service(){}

    public Service_Property getService_property() {
        return service_property;
    }

    public void setService_property(Service_Property service_property) {
        this.service_property = service_property;
    }

    public Package_Detail getPackage_detail() {
        return package_detail;
    }

    public void setPackage_detail(Package_Detail package_detail) {
        this.package_detail = package_detail;
    }

    @Override
    public String toString() {
        return "Package_Service{" + "service_property=" + service_property + ", package_detail=" + package_detail + '}';
    }
    
}
