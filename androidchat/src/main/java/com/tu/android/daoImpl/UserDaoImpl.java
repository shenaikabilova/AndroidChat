package com.tu.android.daoImpl;


import com.tu.android.dao.UserDao;
import com.tu.android.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            final String QUERY = "SELECT id, username, password FROM users WHERE username = '" + username + "' and password = '" + password + "'";

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

    @Override
    public User checkExistUser (String username, String password) {
        User user = null;

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
            final String QUERY = "SELECT id, username, password FROM users WHERE username = '" + username + "' and password = '" + password + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = null;

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AndroidChat", "root", "123456")) {
            final String QUERY = "SELECT id, username FROM users";
            final String QUERY_COUNT = "SELECT COUNT(*) as count FROM users";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            PreparedStatement preparedStatementCount = connection.prepareStatement(QUERY_COUNT);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSetCount = preparedStatementCount.executeQuery();

            resultSetCount.next();
            int count = resultSetCount.getInt("count");

            users = new ArrayList<>(count);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setUsername( resultSet.getString("username"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
