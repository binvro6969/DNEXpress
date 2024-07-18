/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haian
 */
public class Account {

    private int account_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone_numb;
    private Date dob;
    private String cccd_numb;
    private String avatar;
    private char gender;
    private int role;
    private boolean status;

    public Account() {
    }

    public Account(int account_id, String firstName, String lastName, String email, String password, String phone_numb, int role) {
        this.account_id = account_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone_numb = phone_numb;
        this.role = role;
    }
    
    public Account(int account_id, String firstName, String lastName, String email, String password, String phone_numb, Date dob, String cccd_numb, String avatar, char gender, int role, boolean status) {
        this.account_id = account_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone_numb = phone_numb;
        this.dob = dob;
        this.cccd_numb = cccd_numb;
        this.avatar = avatar;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public java.sql.Date getDob_Database() {
        return new java.sql.Date(dob.getTime());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_numb() {
        return phone_numb;
    }

    public void setPhone_numb(String phone_numb) {
        this.phone_numb = phone_numb;
    }

    public String getCccd_numb() {
        return cccd_numb;
    }

    public void setCccd_numb(String cccd_numb) {
        this.cccd_numb = cccd_numb;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        switch (gender) {
            case 'M':
                return "Male";
            case 'F':
                return "Female";
            case 'O':
                return "Other";
            default:
                return "NA";
        }
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" + "account_id=" + account_id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", phone_numb=" + phone_numb + ", dob=" + dob + ", cccd_numb=" + cccd_numb + ", avatar=" + avatar + ", gender=" + gender + ", role=" + role + ", status=" + status + '}';
    }

}
