package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.driver.DbConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoMem implements ProductDao {

    private static Connection cursor = DbConnection.getDb();
    private static ProductDaoMem instance = null;
    private ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
    private SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        try {
            PreparedStatement add = cursor.prepareStatement(
                    "INSERT INTO product" +
                    " (name, product_category_id, supplier_id, price, currency, description) VALUES (?,?,?,?,?,?)",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            add.setString(1, product.getName());
            add.setInt(2, product.getProductCategory().getId());
            add.setInt(3, product.getSupplier().getId());
            add.setFloat(4, product.getDefaultPrice());
            add.setString(5, product.getDefaultCurrency());
            add.setString(6, product.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        try {
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product WHERE id = ?");
            find.setInt(1, id);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new Product(
                    resultSet.getString("name"), resultSet.getFloat("price"),
                    resultSet.getString("currency"), resultSet.getString("description"),
                    productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                    supplierDaoMem.find(resultSet.getInt("supplier_id")),
                    resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product find(String name) {
        try {
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product WHERE name = ?");
            find.setString(1, name);
            find.execute();
            ResultSet resultSet = find.getResultSet();
            resultSet.next();
            return new Product(
                    resultSet.getString("name"), resultSet.getFloat("price"),
                    resultSet.getString("currency"), resultSet.getString("description"),
                    productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                    supplierDaoMem.find(resultSet.getInt("supplier_id")),
                    resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement remove = cursor.prepareStatement("SELECT * FROM product WHERE id = ?",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            remove.setInt(1, id);
            remove.execute();
            ResultSet resultSet = remove.getResultSet();
            resultSet.next();
            resultSet.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product");
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getString("currency"), resultSet.getString("description"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id")),
                        resultSet.getInt("id")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product WHERE supplier_id = ?");
            find.setInt(1, supplier.getId());
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getString("currency"), resultSet.getString("description"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id")),
                        resultSet.getInt("id")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try {
            ArrayList<Product> result = new ArrayList<>();
            PreparedStatement find = cursor.prepareStatement("SELECT * FROM product WHERE product_category_id = ?");
            find.setInt(1, productCategory.getId());
            find.execute();
            ResultSet resultSet = find.getResultSet();
            while (resultSet.next()) {
                result.add(new Product(
                        resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getString("currency"), resultSet.getString("description"),
                        productCategoryDaoMem.find(resultSet.getInt("product_category_id")),
                        supplierDaoMem.find(resultSet.getInt("supplier_id")),
                        resultSet.getInt("id")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
