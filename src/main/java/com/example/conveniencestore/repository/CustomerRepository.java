package com.example.conveniencestore.repository;

import com.example.conveniencestore.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer save(Customer customer);
}
