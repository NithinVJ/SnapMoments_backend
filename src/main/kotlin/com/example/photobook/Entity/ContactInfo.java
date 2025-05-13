package com.example.photobook.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactInfo {
    private String email;
    private String phone;
    private String website;

    public ContactInfo() {
    }

    public ContactInfo(String email, String phone, String website) {
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
