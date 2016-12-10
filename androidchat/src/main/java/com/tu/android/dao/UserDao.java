package com.tu.android.dao;

import com.tu.android.model.User;

/**
 * Created by ShenaiKabilova
 */
public interface UserDao {
    void addUser (User user);
    boolean isUser (String username, String password);
}
