package com.example.convenience_store.service;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return toCustomerResponse(savedCustomer);
    }
    public Customer update(Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(updatedCustomer.getCustomerId()).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setId(updatedCustomer.getId());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            Customer updated = customerRepository.save(existingCustomer);
            return toCustomerResponse(updated);
        }
        return null;
    }

    public Customer read(Integer id) {
        Customer Customer = customerRepository.findById(id).orElse(null);
        if (Customer != null) {
            return toCustomerResponse(Customer);
        }
        return null;
    }

    public void delete(Integer id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            customerRepository.delete(existingCustomer);
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
        List<Customer> Customers = customerRepository.findAll();
        return Customers.stream().map(this::toCustomerResponse).collect(Collectors.toList());
    }
}
