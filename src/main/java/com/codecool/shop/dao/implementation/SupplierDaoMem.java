package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.driver.DbConnection;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private static Connection cursor = DbConnection.getDb();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO supplier (name, description) VALUES (?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setString(1, supplier.getName());
            add.setString(2, supplier.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM supplier WHERE id = ?");
            find.setInt(1,id);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new Supplier(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Supplier find(String name) {
        try {
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM supplier WHERE name = ?");
            find.setString(1,name);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new Supplier(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement remove = cursor.prepareStatement("SELECT * FROM supplier WHERE id = ?",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            remove.setInt(1,id);
            remove.execute();
            ResultSet resultSet = remove.getResultSet();
            resultSet.next();
            resultSet.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        try {
        ArrayList<Supplier> result = new ArrayList<>();
        PreparedStatement find = cursor.prepareStatement("SELECT * FROM supplier");
        find.execute();
        ResultSet resultSet = find.getResultSet();
        while (resultSet.next()) {
            result.add(new Supplier(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("id")));
        }
        return result;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;

    }
}
