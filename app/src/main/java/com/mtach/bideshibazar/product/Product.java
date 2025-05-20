package com.mtach.bideshibazar.product;

import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private int addedToCart;
    private int sold;
    private double rating;
    private double currentPrice;
    private double oldPrice;
    private int discount;
    private int imageResId;

    // Constructor + Getters

    public Product(String name, int addedToCart, int sold, double rating, double currentPrice, double oldPrice, int discount, int imageResId) {
        this.name = name;
        this.addedToCart = addedToCart;
        this.sold = sold;
        this.rating = rating;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.discount = discount;
        this.imageResId = imageResId;
    }

    // Getters...

    public static List<Product> generateDummyGroceries() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        list.add(new Product("PRAN Jhal Muri- jhalmuri", 32, 46, 4.8, 1.10, 1.7, 39, R.drawable.pr2));
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        list.add(new Product("PRAN Jhal Muri- jhalmuri", 32, 46, 4.8, 1.10, 1.7, 39, R.drawable.pr2));
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        list.add(new Product("PRAN Jhal Muri- jhalmuri", 32, 46, 4.8, 1.10, 1.7, 39, R.drawable.pr2));
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        list.add(new Product("PRAN Jhal Muri- jhalmuri", 32, 46, 4.8, 1.10, 1.7, 39, R.drawable.pr2));
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        list.add(new Product("PRAN Jhal Muri- jhalmuri", 32, 46, 4.8, 1.10, 1.7, 39, R.drawable.pr2));
        list.add(new Product("Pran Soft Crunch Toast", 14, 86, 4.7,  3.63, 4.83, 45, R.drawable.pr1));
        // Add more dummy products...
        return list;
    }

    public String getName() { return name; }
    public int getAddedToCart() { return addedToCart; }
    public int getSold() { return sold; }
    public double getRating() { return rating; }
    public double getCurrentPrice() { return currentPrice; }
    public double getOldPrice() { return oldPrice; }
    public int getDiscount() { return discount; }
    public int getImageResId() { return imageResId; }
}

