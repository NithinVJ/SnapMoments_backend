package com.example.photobook.Model;

import com.example.photobook.Entity.Payment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class PaymentModel {
    private Long bookingId;
    private double amount;
    private String paymentMethod;
    private String paymentType;
    private String transactionId;
    private String paymentDetails;
    private String status;

    public PaymentModel() {
    }

    public PaymentModel(double amount, Long bookingId, String paymentMethod, String paymentType, String transactionId, String paymentDetails, String status) {
        this.amount = amount;
        this.bookingId = bookingId;
        this.paymentMethod = paymentMethod;
        this.paymentType = paymentType;
        this.transactionId = transactionId;
        this.paymentDetails = paymentDetails;
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

