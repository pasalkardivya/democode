package com.example.lodgings.repository;

import com.example.lodgings.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findAllByOrderByCreatedAtDesc();
    List<FoodOrder> findByEmailOrderByCreatedAtDesc(String email);
    long countByStatus(String status);
}
