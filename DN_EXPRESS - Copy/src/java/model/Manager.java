/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author haian
 */
public class Manager {
    private int manager_id;
    private Post_Office post_office;
    private Account account;

    public Manager(int manager_id, Post_Office post_office, Account account) {
        this.manager_id = manager_id;
        this.post_office = post_office;
        this.account = account;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
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
    
}
