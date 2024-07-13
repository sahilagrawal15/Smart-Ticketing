package com.ticketing.paymentservice.controller;

import com.ticketing.paymentservice.model.Payment;
import com.ticketing.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(payment);
        return new ResponseEntity<>(processedPayment, HttpStatus.CREATED);
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Payment>> getPaymentsByTicketId(@PathVariable Long ticketId) {
        List<Payment> payments = paymentService.getPaymentsByTicketId(ticketId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
