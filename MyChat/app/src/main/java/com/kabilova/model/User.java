package com.kabilova.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShenaiKabilova.
 */

public class User {
    private int userID;
    private String userEmail;
    private String password;
    private List<User> userFriends = new ArrayList<>();

    public User() {
    }

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public User(int userID, String userEmail, String password) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}