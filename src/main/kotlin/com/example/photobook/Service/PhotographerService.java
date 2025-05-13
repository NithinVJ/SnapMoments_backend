package com.example.photobook.Service;

import com.example.photobook.Entity.PhotographerEntity;
import com.example.photobook.Repository.PhotographerRepository;
import com.example.photobook.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotographerService {

    private static final String UPLOAD_DIR = "uploads/"; // Make sure this folder exists

    @Autowired
    private PhotographerRepository photographerRepository;


    @Autowired
    private UserRepository userRepository;

    public PhotographerEntity savePortfolio(PhotographerEntity portfolio) {
        return photographerRepository.save(portfolio);
    }

    public List<PhotographerEntity> getAllPortfolios() {
        return photographerRepository.findAll();
    }

    public PhotographerEntity getPhotographerById(Long id) {
        return photographerRepository.findById(id).orElse(null);
    }

    @Transactional
    public PhotographerEntity updatePhotographer(Long id, PhotographerEntity photographerData, List<MultipartFile> files) throws IOException {
        PhotographerEntity existingPhotographer = getPhotographerById(id);
        if (existingPhotographer == null) {
            throw new IllegalArgumentException("Photographer with ID " + id + " not found.");
        }

        // Update basic info
        existingPhotographer.setFullName(photographerData.getFullName());
        existingPhotographer.setSpecialty(photographerData.getSpecialty());
        existingPhotographer.setLocation(photographerData.getLocation());
        existingPhotographer.setBio(photographerData.getBio());
        existingPhotographer.setTags(photographerData.getTags());
        existingPhotographer.setPricingPlans(photographerData.getPricingPlans());
        existingPhotographer.setAvailabilityDates(photographerData.getAvailabilityDates());
        if (photographerData.getContactInfo() != null) {
            existingPhotographer.setContactInfo(photographerData.getContactInfo());
        }

        // Update social media
        if (photographerData.getSocialMedia() != null) {
            existingPhotographer.setSocialMedia(photographerData.getSocialMedia());
        }

        // Handle image updates
        if (files != null && !files.isEmpty()) {
            List<String> newImageUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String filename = StringUtils.cleanPath(file.getOriginalFilename());
                    Path uploadPath = Paths.get(UPLOAD_DIR);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Path filePath = uploadPath.resolve(filename);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    newImageUrls.add( filename); // For serving via URL
                }
            }
            existingPhotographer.setImageUrls(newImageUrls);
        }

        return photographerRepository.save(existingPhotographer);
    }
}
