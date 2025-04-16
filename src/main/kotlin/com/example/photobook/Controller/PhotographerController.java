package com.example.photobook.Controller;

import com.example.photobook.Entity.PhotographerEntity;
import com.example.photobook.Service.PhotographerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
