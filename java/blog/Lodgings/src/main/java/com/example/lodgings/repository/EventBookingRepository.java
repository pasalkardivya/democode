package com.example.lodgings.repository;

import com.example.lodgings.entity.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
    List<EventBooking> findAllByOrderByCreatedAtDesc();
    List<EventBooking> findByEmailOrderByCreatedAtDesc(String email);
    long countByStatus(String status);
}
