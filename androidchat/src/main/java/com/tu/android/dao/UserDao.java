package com.tu.android.dao;

import com.tu.android.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by ShenaiKabilova
 */
public interface UserDao {
    void addUser (User user);
    boolean isUser (String username, String password);
    User checkExistUser (String username, String password);
    List<User> getUsers ();
}
