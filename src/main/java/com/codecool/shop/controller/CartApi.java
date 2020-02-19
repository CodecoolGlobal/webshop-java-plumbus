package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartApi extends HttpServlet {
    private ProductDao productDao = ProductDaoMem.getInstance();
    private CartDaoMem cartDao = CartDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartJson = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        JsonCart jsonCart = gson.fromJson(cartJson, JsonCart.class);
        for (Map.Entry<String, CartProduct> product : jsonCart.items.entrySet()) {
            for (int i = 0; i < product.getValue().quantity; i++) {
                cartDao.add(productDao.find(product.getValue().name));
            }
        }

    }

    public class JsonCart {
        public HashMap<String, CartProduct> items;

    }

    public class CartProduct {
        public String name;
        public Integer quantity;
    }




}
