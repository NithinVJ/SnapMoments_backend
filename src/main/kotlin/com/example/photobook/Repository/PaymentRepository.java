package com.example.photobook.Repository;

import com.example.photobook.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.bookingId = :bookingId")
    double findTotalPaidByBookingId(@Param("bookingId") Long bookingId);
}

