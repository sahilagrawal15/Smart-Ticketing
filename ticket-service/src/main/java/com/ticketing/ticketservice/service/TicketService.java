package com.ticketing.ticketservice.service;

import com.ticketing.ticketservice.model.Ticket;
import com.ticketing.ticketservice.repository.TicketRepository;
import com.ticketing.ticketservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "ticket-events";

    public Ticket bookTicket(Ticket ticket) {
        Ticket bookedTicket = ticketRepository.save(ticket);
        kafkaTemplate.send(TOPIC, "Ticket booked: " + bookedTicket.toString());
        return bookedTicket;
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + id));
        ticket.setSeatNumber(ticketDetails.getSeatNumber());
        ticket.setPrice(ticketDetails.getPrice());
        Ticket updatedTicket = ticketRepository.save(ticket);
        kafkaTemplate.send(TOPIC, "Ticket updated: " + updatedTicket.toString());
        return updatedTicket;
    }

    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + id));
        ticketRepository.delete(ticket);
        kafkaTemplate.send(TOPIC, "Ticket deleted: " + ticket.toString());
    }
}
