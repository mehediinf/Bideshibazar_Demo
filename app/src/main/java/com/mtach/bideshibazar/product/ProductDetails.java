package com.mtach.bideshibazar.product;

public class ProductDetails {
    private String name;
    private double price;
    private String description;
    private String imageUrl; // যদি API থেকে আসে

    // Constructor
    public ProductDetails(String name, double price, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
}

