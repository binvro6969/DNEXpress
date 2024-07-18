 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Customer {
    private Account account;
    private int customer_id;

    public Customer(Account account, int customer_id) {
        this.account = account;
        this.customer_id = customer_id;
    }

    public Customer() {
        
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Customer{" + "account=" + account + ", customer_id=" + customer_id + '}';
    }
    
     
}
