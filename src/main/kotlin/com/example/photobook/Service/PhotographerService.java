package com.example.photobook.Service;

import com.example.photobook.Entity.PhotographerEntity;
import com.example.photobook.Repository.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographerService {

    @Autowired
    private PhotographerRepository photographerRepository;

    public PhotographerEntity savePortfolio(PhotographerEntity portfolio) {
        return photographerRepository.save(portfolio);
    }

    public List<PhotographerEntity> getAllPortfolios() {
        return photographerRepository.findAll();
    }
}
