package com.example.photobook.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name="photographers")
@JsonIgnoreProperties(ignoreUnknown = true)

public class PhotographerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String specialty;
    private String location;

    @Column(length = 1000)
    private String bio;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> availabilityDates;

    @ElementCollection
    private List<String> imageUrls; // Stores image file names/URLs

    @Column(length = 1000)
    private String pricingInfo;

    public PhotographerEntity() {
    }

    public PhotographerEntity(Long id, String fullName, String specialty, String location, String bio, List<String> tags, List<String> availabilityDates, List<String> imageUrls, String pricingInfo) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.location = location;
        this.bio = bio;
        this.tags = tags;
        this.availabilityDates = availabilityDates;
        this.imageUrls = imageUrls;
        this.pricingInfo = pricingInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getAvailabilityDates() {
        return availabilityDates;
    }

    public void setAvailabilityDates(List<String> availabilityDates) {
        this.availabilityDates = availabilityDates;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getPricingInfo() {
        return pricingInfo;
    }

    public void setPricingInfo(String pricingInfo) {
        this.pricingInfo = pricingInfo;
    }
}
