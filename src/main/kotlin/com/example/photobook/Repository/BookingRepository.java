package com.example.photobook.Repository;
import com.example.photobook.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Optional<BookingEntity> findByBookingCode(String bookingCode);

    List<BookingEntity> findByUserId(Long userId);

    List<BookingEntity> findByPhotographerId(Long photographerId);

    List<BookingEntity> findByStatus(String status);

    List<BookingEntity> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
}
