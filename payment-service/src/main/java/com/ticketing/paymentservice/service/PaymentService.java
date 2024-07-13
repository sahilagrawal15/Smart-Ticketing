package com.ticketing.paymentservice.service;

import com.ticketing.paymentservice.model.Payment;
import com.ticketing.paymentservice.repository.PaymentRepository;
import com.ticketing.paymentservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "payment-events";

    public Payment processPayment(Payment payment) {
        Payment processedPayment = paymentRepository.save(payment);
        kafkaTemplate.send(TOPIC, "Payment processed: " + processedPayment.toString());
        return processedPayment;
    }

    public List<Payment> getPaymentsByTicketId(Long ticketId) {
        return paymentRepository.findByTicketId(ticketId);
    }
}
