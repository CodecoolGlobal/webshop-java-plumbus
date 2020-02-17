package com.codecool.shop;
import com.codecool.shop.db.FileReader;
import java.util.HashMap;
import java.sql.*;
import java.util.Properties;

class Main {
    public static void main(String[] args) {
        HashMap<String, String> data = new HashMap<>();
        FileReader fileReader = new FileReader();
        String file = fileReader.readLineByLine(Main.class.getResource("/db/config.txt").getPath());
        String[] lines = file.split("\n");
        for (String line :
                lines) {
            String[] currentLine = line.split("=");
            data.put(currentLine[0], currentLine[1]);
        }
        System.out.println(data.toString());


        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + data.get("url") + "/" + data.get("database");
            Properties props = new Properties();
            props.setProperty("user", data.get("user"));
            props.setProperty("password", data.get("password"));
            Connection db = DriverManager.getConnection(url, props);
            Statement statement = db.createStatement();
            PreparedStatement preparedStatement = db.prepareStatement("SELECT * FROM users",
            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("name"));
            resultSet.deleteRow();
        } catch(ClassNotFoundException  ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException ex ) {
            System.out.println(ex.toString());
        }


    }
}