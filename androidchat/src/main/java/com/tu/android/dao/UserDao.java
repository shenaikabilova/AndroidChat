package com.tu.android.dao;

import com.tu.android.model.User;

import java.util.Set;

/**
 * Created by ShenaiKabilova
 */
public interface UserDao {
    void addUser (User user);
    boolean isUser (String username, String password);
    Set<User> getUsers ();
}
