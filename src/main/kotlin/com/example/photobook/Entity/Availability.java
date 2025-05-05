package com.example.photobook.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Availability {
    private String date;
    private String status;

    public Availability() {
    }

    public Availability(String date, String status) {
        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
