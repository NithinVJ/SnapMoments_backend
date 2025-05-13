package com.example.photobook.Controller;

import com.example.photobook.Model.PaymentModel;
import com.example.photobook.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentModel paymentModel) {
        paymentService.processPayment(paymentModel);
        return ResponseEntity.ok("Payment successful");
    }

    @GetMapping("/totalPaid/{bookingId}")
    public ResponseEntity<Double> getTotalPaidAmount(@PathVariable Long bookingId) {
        double totalPaid = paymentService.getTotalPaidAmount(bookingId);
        return ResponseEntity.ok(totalPaid);
    }
    @PutMapping("/{paymentId}/status")
    public ResponseEntity<String> updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestBody Map<String, String> requestBody) {

        String status = requestBody.get("status");
        paymentService.updatePaymentStatus(paymentId, status);
        return ResponseEntity.ok("Payment status updated successfully");
    }

}

