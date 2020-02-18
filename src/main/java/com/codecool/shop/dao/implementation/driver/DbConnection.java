package com.codecool.shop.dao.implementation.driver;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DbConnection {

    private static String user;
    private static String password;
    private static String url;
    private static Connection cursor;

    private static void connectDb() {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        try {
            cursor = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setProperties() {
        HashMap<String, String> data = new HashMap<>();
        String file = FileReader.readLineByLine(DbConnection.class.getResource("/db/config.txt").getPath());
        String[] lines = file.split("\n");
        for (String line :
                lines) {
            String[] currentLine = line.split("=");
            data.put(currentLine[0], currentLine[1]);
        }
        user = data.get("user");
        password = data.get("password");
        url = "jdbc:postgresql://" + data.get("url") + "/" + data.get("database");
    }

    public static Connection getDb() {
        setProperties();
        connectDb();
        return cursor;
    }

}
