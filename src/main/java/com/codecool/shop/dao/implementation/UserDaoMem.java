package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.driver.DbConnection;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMem implements UserDao {
    private static UserDaoMem instance = null;
    private static Connection cursor = DbConnection.getDb();

    private UserDaoMem() {}

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance =  new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO users (name, password) VALUES (?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setString(1, user.getName());
            add.setString(2, user.getPassword());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
            find.setInt(1, id);
            find.execute();

            ResultSet resultSet = find.getResultSet();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(String name) {
        try {
            PreparedStatement find = cursor.prepareStatement(
                    "SELECT * FROM users WHERE name = ?");
            find.setString(1, name);
            find.execute();

            ResultSet resultSet = find.getResultSet();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement remove = cursor.prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
            remove.setInt(1, id);
            remove.execute();

            ResultSet resultSet = remove.getResultSet();
            if (resultSet.next()) resultSet.deleteRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try {
            PreparedStatement getAll = cursor.prepareStatement(
                    "SELECT * FROM users");
            getAll.execute();

            ResultSet resultSet = getAll.getResultSet();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"));
                allUsers.add(user);
            }
            return allUsers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
