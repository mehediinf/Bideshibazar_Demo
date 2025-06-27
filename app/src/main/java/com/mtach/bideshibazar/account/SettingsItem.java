package com.mtach.bideshibazar.account;

public class SettingsItem {
    private String title;
    private String label; // Extra label on the right side (optional)

    public SettingsItem(String title, String label) {
        this.title = title;
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }
}
