package com.ticketing.consumerservice.listener;

import com.ticketing.consumerservice.model.Event;
import com.ticketing.consumerservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListeners {

    @Autowired
    private EventRepository eventRepository;

    @KafkaListener(topics = "user-events", groupId = "group_id")
    public void consumeUserEvents(String message) {
        System.out.println("Consumed user event: " + message);
        Event event = new Event();
        event.setEventType("User");
        event.setEventData(message);
        eventRepository.save(event);
    }

    @KafkaListener(topics = "ticket-events", groupId = "group_id")
    public void consumeTicketEvents(String message) {
        System.out.println("Consumed ticket event: " + message);
        Event event = new Event();
        event.setEventType("Ticket");
        event.setEventData(message);
        eventRepository.save(event);
    }

    @KafkaListener(topics = "payment-events", groupId = "group_id")
    public void consumePaymentEvents(String message) {
        System.out.println("Consumed payment event: " + message);
        Event event = new Event();
        event.setEventType("Payment");
        event.setEventData(message);
        eventRepository.save(event);
    }
}
