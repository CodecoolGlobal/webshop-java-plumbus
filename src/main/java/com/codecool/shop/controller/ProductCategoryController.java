package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/category"})
public class ProductCategoryController extends HttpServlet {
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = new Gson().toJson(productCategoryDataStore.getAll());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

}
