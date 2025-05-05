package com.example.photobook.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Pricing {
    private String title;
    private String price;
    private String details;

    private boolean popular;

    public Pricing() {
    }

    public Pricing(String title, String price, String details, boolean popular) {
        this.title = title;
        this.price = price;
        this.details = details;
        this.popular = popular;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
}
