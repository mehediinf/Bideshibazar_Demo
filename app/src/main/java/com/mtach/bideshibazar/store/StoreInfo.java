package com.mtach.bideshibazar.store;

public class StoreInfo {
    private String name;
    private String address;
    private String contact;
    private String hotline;

    // Constructor, Getter & Setter
    public StoreInfo(String name, String address, String contact, String hotline) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.hotline = hotline;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getHotline() {
        return hotline;
    }
}
