/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Staff {
    private int staff_id;
    private Post_Office post_office;
    private Account account;

    public Staff(int staff_id, Post_Office post_office, Account account) {
        this.staff_id = staff_id;
        this.post_office = post_office;
        this.account = account;
    }

    public Staff(Post_Office post_office, Account account) {
        this.post_office = post_office;
        this.account = account;
    }
    
    public Staff(){}
    

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public Post_Office getPost_office() {
        return post_office;
    }

    public void setPost_office(Post_Office post_office) {
        this.post_office = post_office;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    } 

    @Override
    public String toString() {
        return "Staff{" + "staff_id=" + staff_id + ", post_office=" + post_office + ", account=" + account + '}';
    }
    
}
