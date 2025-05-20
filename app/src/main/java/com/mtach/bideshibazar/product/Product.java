package com.mtach.bideshibazar.product;

import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private final String name;
    private final int addedToCart;
    private final int sold;
    private final double rating;
    private final double currentPrice;
    private final double oldPrice;
    private final int discount;
    private final int imageResId;

    public Product(String name, int addedToCart, int sold, double rating,
                   double currentPrice, double oldPrice, int discount, int imageResId) {
        this.name = name;
        this.addedToCart = addedToCart;
        this.sold = sold;
        this.rating = rating;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.discount = discount;
        this.imageResId = imageResId;
    }

    // 🔄 Generic dummy generator with pagination support
    public static List<Product> generateDummyData(String category, int page) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * 10;
        int end = start + 10;

        for (int i = start; i < end; i++) {
            String name = category + " Product " + (i + 1);
            int addedToCart = 5 + (i % 10);
            int sold = 50 + (i % 30);
            double rating = 3.5 + (i % 5) * 0.1;
            double currentPrice = 10 + (i % 10) * 1.5;
            double oldPrice = currentPrice + 5;
            int discount = (int) ((oldPrice - currentPrice) / oldPrice * 100);
            int imageResId = getImageByCategory(category);

            list.add(new Product(name, addedToCart, sold, rating, currentPrice, oldPrice, discount, imageResId));
        }

        return list;
    }

    // 🔁 Helper method for image selection by category
    private static int getImageByCategory(String category) {
        if (category == null) return R.drawable.font_page;

        switch (category.toLowerCase()) {
            case "groceries":
                return R.drawable.pr1;
            case "fashion":
                return R.drawable.fashion;
            case "tickets":
                return R.drawable.basket;
            default:
                return R.drawable.font_page;
        }
    }

    // 👇 Static generators for fixed data (optional use)
    public static List<Product> generateDummyGroceries() {
        return generateDummyData("groceries", 1);
    }

    public static List<Product> generateDummyFashion() {
        return generateDummyData("fashion", 1);
    }

    public static List<Product> generateDummyTickets() {
        return generateDummyData("tickets", 1);
    }

    // ✅ Getter methods
    public String getName() { return name; }
    public int getAddedToCart() { return addedToCart; }
    public int getSold() { return sold; }
    public double getRating() { return rating; }
    public double getCurrentPrice() { return currentPrice; }
    public double getOldPrice() { return oldPrice; }
    public int getDiscount() { return discount; }
    public int getImageResId() { return imageResId; }
}
