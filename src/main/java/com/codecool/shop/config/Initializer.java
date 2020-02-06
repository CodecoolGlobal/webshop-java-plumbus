package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.CartDaoMem;
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
        Supplier apple = new Supplier("Apple", "Consumer electronics");
        Supplier gigabyte = new Supplier("Gigabyte", "Computer parts");
        Supplier amd = new Supplier("AMD", "Processing units");
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        supplierDataStore.add(raspberry);
        supplierDataStore.add(arduino);
        supplierDataStore.add(asus);
        supplierDataStore.add(apple);

        //setting up a new product category
        ProductCategory sbc = new ProductCategory("Single Board Computer", "Hardware", "Single board computers are used for lightweight tasks, they need very little amount of power.");
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "Notebooks are reliable way of having a computer with you on the go.");
        ProductCategory gpu = new ProductCategory("Graphics Card", "Hardware", "Graphics card are mainly used for gaming, it boosts the graphical processing power of the computer");
        ProductCategory cpu = new ProductCategory("CPU", "Hardware", "Central processing units are the brain of computers");
        productCategoryDataStore.add(sbc);
        productCategoryDataStore.add(notebook);
        productCategoryDataStore.add(gpu);
        productCategoryDataStore.add(cpu);

        //setting up products and printing it
        productDataStore.add(new Product("Raspberry Pi 3B", 48.85f, "USD", "The Raspberry Pi 3 Model B is the earliest model of the third-generation Raspberry Pi.", sbc, raspberry));
        productDataStore.add(new Product("Raspberry Pi 3B+", 55.96f, "USD", "The Raspberry Pi 3 Model B+ is the highest performing model in this generation of SBC-s", sbc, raspberry));
        productDataStore.add(new Product("Raspberry Pi 4B [4GB]", 76.99f, "USD", "You'll recognise the price along with the basic shape and size, so you can simply drop your new Raspberry Pi into your old projects for an upgrade", sbc, raspberry));
        productDataStore.add(new Product("Arduino Uno R3", 18, "USD", "Great for learning the basics of how sensors and actuators work, and an essential tool for your rapid prototyping needs", sbc, arduino));
        productDataStore.add(new Product("Lenovo IdeaPad S145", 466.9f, "USD", "This notebook is designed for long time portable performance, with its i5 processor", notebook, lenovo));
        productDataStore.add(new Product("Lenovo Legion Y540", 998.99f, "USD", "This gaming laptop has the top of the line performance for the best price on the market right now, you games definetly won't lag.", notebook, lenovo));
        productDataStore.add(new Product("ASUS ROG Strix G531GT", 999.9f, "USD", "Join the Republic Of Gamers with this laptop, its cooling solution will definetly last longer then your gaming session", notebook, asus));
        productDataStore.add(new Product("Apple MacBook Pro 16", 2999.9f, "USD", "Apple's 16-inch MacBook Pro is basically every creative's dream machine, with a ton of power and a vastly improved keyboard.", notebook, apple));
        productDataStore.add(new Product("Raspberry Pi 3A+", 24.9f, "USD", "The Raspberry Pi 3 Model A+ is a smaller version of the B model, with less processing power, but it's nice if you're in need of space", sbc, raspberry));
        productDataStore.add(new Product("Gigabyte GeForce GTX 1660 Ti OC 6GB", 319.9f, "USD", "This unit has a Windforce cooling system, that keeps the gpu cool for long sessions.", gpu, gigabyte));
        productDataStore.add(new Product("GIGABYTE GeForce GTX 1050 Ti OC 4GB", 165, "USD", "Most popular choice among low budget gamers", gpu, gigabyte));
        productDataStore.add(new Product("GIGABYTE GeForce RTX 2060 SUPER AORUS 8GB", 519.9f, "USD", "Nice looking Graphics Card with RGB lighting", gpu, gigabyte));
        productDataStore.add(new Product("AMD Ryzen 5 3600 Hexa-Core 3.6GHz AM4 Processor", 209.9f, "USD", "Get the best performance per dollar out of a CPU with this processor", cpu, amd));
        productDataStore.add(new Product("AMD Ryzen 7 3700x Octa-Core 3.6GHz AM4", 399.9f, "USD", "Get the best 8 core CPU on the market right now.", cpu, amd));



    }
}
