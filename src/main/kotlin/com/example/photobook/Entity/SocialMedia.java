package com.example.photobook.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class SocialMedia {
    private String instagram;
    private String facebook;
    private String twitter;

    public SocialMedia() {
    }

    public SocialMedia(String instagram, String facebook, String twitter) {
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
