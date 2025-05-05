package com.example.photobook.Repository;

import com.example.photobook.Entity.PhotographerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotographerRepository extends JpaRepository<PhotographerEntity,Long> {
    Optional<PhotographerEntity> findByUserId(Long userId);
}





