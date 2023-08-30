package com.example.conveniencestore.model.entity;

import lombok.*;

import javax.persistence.*;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String id;

    private String password;

    private String name;

}

