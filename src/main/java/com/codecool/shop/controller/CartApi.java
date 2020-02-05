package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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
        List<Product> currentCart = new ArrayList<>();
        String cartJson = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        Cart cart = gson.fromJson(cartJson, Cart.class);
        for (Map.Entry<String, CartProduct> product : cart.items.entrySet()) {
            for (int i = 0; i < product.getValue().quantity; i++) {
                currentCart.add(productDao.find(product.getValue().name));
            }
        }
        cartDao.clear();
        for (Product item : currentCart) {
            cartDao.add(item);
        }
    }

    public class Cart {
        public HashMap<String, CartProduct> items;

    }

    public class CartProduct {
        public String name;
        public Integer quantity;
    }




}
