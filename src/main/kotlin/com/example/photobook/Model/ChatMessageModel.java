package com.example.photobook.Model;


import java.time.LocalDateTime;


public class ChatMessageModel {
    private String bookingId;
    private String senderToken; // Stores the dummy token ("user_12345")
    private String senderName;
    private String senderRole; // "CLIENT" or "PHOTOGRAPHER"
    private String recipientToken;
    private String recipientName;
    private String recipientRole;
    private String content;
    private boolean isRead = false;
    private LocalDateTime timestamp;

    public ChatMessageModel() {
    }

    public ChatMessageModel(String bookingId, String senderToken, String senderName, String senderRole, String recipientToken, String recipientName, String recipientRole, String content, boolean isRead, LocalDateTime timestamp) {
        this.bookingId = bookingId;
        this.senderToken = senderToken;
        this.senderName = senderName;
        this.senderRole = senderRole;
        this.recipientToken = recipientToken;
        this.recipientName = recipientName;
        this.recipientRole = recipientRole;
        this.content = content;
        this.isRead = isRead;
        this.timestamp = timestamp;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSenderToken() {
        return senderToken;
    }

    public void setSenderToken(String senderToken) {
        this.senderToken = senderToken;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getRecipientToken() {
        return recipientToken;
    }

    public void setRecipientToken(String recipientToken) {
        this.recipientToken = recipientToken;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientRole() {
        return recipientRole;
    }

    public void setRecipientRole(String recipientRole) {
        this.recipientRole = recipientRole;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
