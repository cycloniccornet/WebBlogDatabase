package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class User {



    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private int userId;
    private String admin;

    public User(String firstName, String lastName, String password, String username, String admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.admin = admin;
    }

    public User() {
    }

    public User(String password, String username, String admin) {
        this.password = password;
        this.username = username;
        this.admin = admin;
    }

    public User(String password, String username, int userId, String admin) {
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.admin = admin;
    }

    public User(String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String password, String username, int userId, String admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.admin = admin;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName(){
        return firstName + " " + lastName;
    }


}
