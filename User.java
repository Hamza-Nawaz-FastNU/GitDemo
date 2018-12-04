/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Aiman Nawaz
 */
public class User {
    
    String email;
    String phone;
    String password;

    public User(String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    public void displayUser()
    {
        System.out.println("");
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("Phone : " + phone);
    }
}
