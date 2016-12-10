package com.tu.android.daoImpl;


import com.tu.android.dao.UserDao;
import com.tu.android.model.User;

import java.sql.*;

/**
 * Created by ShenaiKabilova
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
            final String QUERY = "INSERT INTO users(username, password) VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUser(String username, String password) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
            final String QUERY = "SELECT username, password FROM users WHERE username = '" + username + "' and password = '" + password + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
