package com.mtach.bideshibazar.account;

public class OrderModel {
    public String sl, orderNo, shopName, subtotal, deliveryCharge, total, status, trackingUrl, action;

    public OrderModel(String sl, String orderNo, String shopName, String subtotal,
                      String deliveryCharge, String total, String status, String trackingUrl, String action) {
        this.sl = sl;
        this.orderNo = orderNo;
        this.shopName = shopName;
        this.subtotal = subtotal;
        this.deliveryCharge = deliveryCharge;
        this.total = total;
        this.status = status;
        this.trackingUrl = trackingUrl;
        this.action = action;
    }
}