package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        Supplier lenovo = new Supplier("Lenovo", "Notebooks");
        Supplier raspberry = new Supplier("RaspberryPi", "Single board computers");
        Supplier arduino = new Supplier("Arduino", "Micro controllers");
        Supplier asus = new Supplier("ASUS", "Notebooks");
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        supplierDataStore.add(raspberry);
        supplierDataStore.add(arduino);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory sbc = new ProductCategory("Single Board Computer", "Hardware", "Single board computers are used for lightweight tasks, they need very little amount of power.");
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "Notebooks are reliable way of having a computer with you on the go.");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(sbc);
        productCategoryDataStore.add(notebook);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Raspberry Pi 3B", 48.85f, "USD", "The Raspberry Pi 3 Model B is the earliest model of the third-generation Raspberry Pi.", sbc, raspberry));
        productDataStore.add(new Product("Raspberry Pi 3B+", 55.96f, "USD", "The Raspberry Pi 3 Model B+ is the highest performing model in this generation of SBC-s", sbc, raspberry));
        productDataStore.add(new Product("Raspberry Pi 4B [4GB]", 76.99f, "USD", "You'll recognise the price along with the basic shape and size, so you can simply drop your new Raspberry Pi into your old projects for an upgrade", sbc, raspberry));
        productDataStore.add(new Product("Arduino Uno R3", 18, "USD", "Great for learning the basics of how sensors and actuators work, and an essential tool for your rapid prototyping needs", sbc, arduino));
        productDataStore.add(new Product("Lenovo IdeaPad S145", 466.99f, "USD", "This notebook is designed for long time portable performance, with its i5 processor", notebook, lenovo));
        productDataStore.add(new Product("Lenovo Legion Y540", 998.99f, "USD", "This gaming laptop has the top of the line performance for the best price on the market right now, you games definetly won't lag.", notebook, lenovo));
    }
}
