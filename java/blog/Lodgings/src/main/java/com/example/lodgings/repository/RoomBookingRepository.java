package com.example.lodgings.repository;

import com.example.lodgings.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    List<RoomBooking> findAllByOrderByCreatedAtDesc();
    List<RoomBooking> findByEmailOrderByCreatedAtDesc(String email);
    long countByStatus(String status);
}
