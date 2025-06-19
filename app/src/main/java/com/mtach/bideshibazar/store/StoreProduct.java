package com.mtach.bideshibazar.store;


public class StoreProduct {
    private final String name;
    private final String addedToCart;
    private final String soldAndRating;
    private final String currentPrice;
    private final String oldPrice;
    private final String discount;
    private final int imageResId;

    public StoreProduct(String name, String addedToCart, String soldAndRating,
                       String currentPrice, String oldPrice, String discount, int imageResId) {
        this.name = name;
        this.addedToCart = addedToCart;
        this.soldAndRating = soldAndRating;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.discount = discount;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getAddedToCart() { return addedToCart; }
    public String getSoldAndRating() { return soldAndRating; }
    public String getCurrentPrice() { return currentPrice; }
    public String getOldPrice() { return oldPrice; }
    public String getDiscount() { return discount; }
    public int getImageResId() { return imageResId; }
}
