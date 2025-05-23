package com.example.photobook.Model;

import com.example.photobook.Entity.Availability;
import com.example.photobook.Entity.Pricing;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PhotographerModel {
    private String fullName;
    private String specialty;
    private String location;
    private String bio;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "photographer_availability", joinColumns = @JoinColumn(name = "photographer_id"))
    private List<Availability> availabilityDates;

    @ElementCollection
    private List<String> imageUrls;

    @ElementCollection
    @CollectionTable(name = "photographer_pricing", joinColumns = @JoinColumn(name = "photographer_id"))
    private List<Pricing> pricingPlans;

    public PhotographerModel() {
    }

    public PhotographerModel(String fullName, String specialty, String location, String bio, List<String> tags, List<Availability> availabilityDates, List<String> imageUrls, List<Pricing> pricingPlans) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.location = location;
        this.bio = bio;
        this.tags = tags;
        this.availabilityDates = availabilityDates;
        this.imageUrls = imageUrls;
        this.pricingPlans = pricingPlans;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Availability> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(List<Availability> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Pricing> getPricingPlans() {
        return pricingPlans;
    }

    public void setPricingPlans(List<Pricing> pricingPlans) {
        this.pricingPlans = pricingPlans;
    }
}
