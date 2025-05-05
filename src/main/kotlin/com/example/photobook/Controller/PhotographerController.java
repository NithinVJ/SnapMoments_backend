package com.example.photobook.Controller;

import com.example.photobook.Entity.PhotographerEntity;
import com.example.photobook.Entity.UserEntity;
import com.example.photobook.Repository.PhotographerRepository;
import com.example.photobook.Repository.UserRepository;
import com.example.photobook.Service.PhotographerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/photographer")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotographerController {

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Autowired
    private PhotographerService photographerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPortfolio(
            @RequestPart("portfolio") String portfolioJson,
            @RequestPart("images") MultipartFile[] images
    ) {
        try {
            // Convert JSON string to PhotographerEntity
            PhotographerEntity portfolio = objectMapper.readValue(portfolioJson, PhotographerEntity.class);

            // Save images and collect URLs
            List<String> imageUrls = new ArrayList<>();
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            for (MultipartFile file : images) {
                String filename = StringUtils.cleanPath(file.getOriginalFilename());
                Path filePath = Paths.get(UPLOAD_DIR + filename);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imageUrls.add(filename);
            }

            portfolio.setImageUrls(imageUrls);

            PhotographerEntity saved = photographerService.savePortfolio(portfolio);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error uploading portfolio.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhotographerEntity>> getAllPortfolios() {
        return ResponseEntity.ok(photographerService.getAllPortfolios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotographerEntity> getPhotographerById(@PathVariable Long id) {
        PhotographerEntity photographer = photographerService.getPhotographerById(id);
        if (photographer != null) {
            return ResponseEntity.ok(photographer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getPhotographerByUserId(@PathVariable Long userId) {
        // Check if user exists
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid user ID: " + userId);
        }

        // Fetch photographer by user
        Optional<PhotographerEntity> photographerOpt = photographerRepository.findByUserId(userId);
        if (!photographerOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No photographer profile found for user ID: " + userId);
        }

        return ResponseEntity.ok(photographerOpt.get());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePhotographer(
            @PathVariable Long id,
            @RequestPart("photographer") String photographerJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> files) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PhotographerEntity photographerData = objectMapper.readValue(photographerJson, PhotographerEntity.class);

            PhotographerEntity updatedPhotographer = photographerService.updatePhotographer(id, photographerData, files);
            return ResponseEntity.ok(updatedPhotographer);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid photographer data format");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating photographer");
        }
    }
}

