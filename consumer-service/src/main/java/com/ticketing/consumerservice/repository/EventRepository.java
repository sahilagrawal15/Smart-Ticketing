package com.ticketing.consumerservice.repository;

import com.ticketing.consumerservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
