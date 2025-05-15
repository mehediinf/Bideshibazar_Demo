package com.mtach.bideshibazar;

public class Product {
    private final int imageRes;
    private final String name;
    private final String price;

    public Product(int imageRes, String name, String price) {
        this.imageRes = imageRes;
        this.name = name;
        this.price = price;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
