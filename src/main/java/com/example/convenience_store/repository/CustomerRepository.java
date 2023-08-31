package com.example.convenience_store.repository;

import com.example.convenience_store.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer save(Customer customer);
}
