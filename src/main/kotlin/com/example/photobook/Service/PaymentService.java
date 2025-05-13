package com.example.photobook.Service;

import com.example.photobook.Entity.Payment;
import com.example.photobook.Model.PaymentModel;
import com.example.photobook.Repository.BookingRepository;
import com.example.photobook.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void processPayment(PaymentModel model) {
        Payment payment = new Payment();
        payment.setBookingId(model.getBookingId());
        payment.setAmount(model.getAmount());
        payment.setPaymentMethod(model.getPaymentMethod());
        payment.setPaymentType(model.getPaymentType());
        payment.setTransactionId(model.getTransactionId());
        payment.setPaymentDetails(model.getPaymentDetails());
        payment.setStatus(model.getStatus());
        payment.setTimestamp(LocalDateTime.now());
        paymentRepository.save(payment);
    }
    
    public void updatePaymentStatus(Long bookingId, String status) {
        Payment payment = paymentRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + bookingId));
        payment.setStatus(status);
        paymentRepository.save(payment);
    }

    public double getTotalPaidAmount(Long bookingId) {
        return paymentRepository.findTotalPaidByBookingId(bookingId);
    }




}



