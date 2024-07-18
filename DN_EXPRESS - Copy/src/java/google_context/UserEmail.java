/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package google_context;


import model.Account;

/**
 *
 * @author haian
 */
public class UserEmail {
    private Account account;
    private String code;
    
    public UserEmail(Account account, String code) {
        this.account = account;
        this.code = code;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
              
}
