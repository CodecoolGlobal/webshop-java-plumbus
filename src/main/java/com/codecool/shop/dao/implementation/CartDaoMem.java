package com.codecool.shop.dao.implementation;


import com.codecool.shop.controller.CartApi;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.driver.DbConnection;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartDaoMem implements CartDao {

    private List<Product> data = new ArrayList<>();
    private static Connection cursor = DbConnection.getDb();
    private static CartDaoMem instance = null;
    private UserDaoMem userDaoMem = UserDaoMem.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Cart cart) {
        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO cart (product_id, users_id, quantity) VALUES (?,?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setInt(1, cart.getProductId());
            add.setInt(2, cart.getUserId());
            add.setInt(3, cart.getQuantity());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement(
                    "SELECT * FROM cart WHERE id = ?");
            find.setInt(1, id);
            find.execute();

            ResultSet resultSet = find.getResultSet();
            if (resultSet.next()) {
                return new Cart(resultSet.getInt("product_id"),
                        resultSet.getInt("users_id"),
                        resultSet.getInt("quantity"));
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
                    "SELECT * FROM cart WHERE id = ?");
            remove.setInt(1, id);
            remove.execute();

            ResultSet resultSet = remove.getResultSet();
            if (resultSet.next()) resultSet.deleteRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cart> getAll() {
        List<Cart> allCarts = new ArrayList<>();
        try {
            PreparedStatement getAll = cursor.prepareStatement(
                    "SELECT * FROM cart");
            getAll.execute();

            ResultSet resultSet = getAll.getResultSet();

            while (resultSet.next()) {
                allCarts.add(new Cart(resultSet.getInt("product_id"),
                        resultSet.getInt("users_id"),
                        resultSet.getInt("quantity")));
            }
            return allCarts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear() {
        try {
            PreparedStatement clear = cursor.prepareStatement(
                    "SELECT * FROM cart");
            clear.execute();

            ResultSet resultSet = clear.getResultSet();
            while (resultSet.next()) {
                resultSet.deleteRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
