package com.example.lodgings.repository;

import com.example.lodgings.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}