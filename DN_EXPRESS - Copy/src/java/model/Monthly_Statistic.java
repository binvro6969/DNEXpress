/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dangq
 */
public class Monthly_Statistic {

    private int year;
    private int month;
    private int orderCount;
    private double avgRating;
    private double totalAmount;

    public Monthly_Statistic(int year, int month, int orderCount, double avgRating, double totalAmount) {
        this.year = year;
        this.month = month;
        this.orderCount = orderCount;
        this.avgRating = avgRating;
        this.totalAmount = totalAmount;
    }

    // Add getters if needed
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
