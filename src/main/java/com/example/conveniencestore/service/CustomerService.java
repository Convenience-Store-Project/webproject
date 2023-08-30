package com.example.conveniencestore.service;

import com.example.conveniencestore.model.entity.Customer;
import com.example.conveniencestore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRepository;

    public Customer create(Customer Customer) {
        Customer savedCustomer = CustomerRepository.save(Customer);
        return toCustomerResponse(savedCustomer);
    }
    public Customer update(Customer updatedCustomer) {
        Customer existingCustomer = CustomerRepository.findById(updatedCustomer.getCustomerId()).orElse(null);
        System.out.println("1111111111");
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setId(updatedCustomer.getId());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            Customer updated = CustomerRepository.save(existingCustomer);
            return toCustomerResponse(updated);
        }
        return null;
    }

    public Customer read(Integer id) {
        Customer Customer = CustomerRepository.findById(id).orElse(null);
        if (Customer != null) {
            return toCustomerResponse(Customer);
        }
        return null;
    }

    public void delete(Integer id) {
        Customer existingCustomer = CustomerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            CustomerRepository.delete(existingCustomer);
        }
    }

    private Customer toCustomerResponse(Customer Customer) {
        return Customer.builder()
                .customerId(Customer.getCustomerId())
                .name(Customer.getName())
                .id(Customer.getId())
                .password(Customer.getPassword())
                .build();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> Customers = CustomerRepository.findAll();
        return Customers.stream().map(this::toCustomerResponse).collect(Collectors.toList());
    }
}
