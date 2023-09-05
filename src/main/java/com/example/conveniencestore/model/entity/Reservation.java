package com.example.conveniencestore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private Integer customerId;

    private Integer productId;

    private Integer storeId;

    private Integer quantity;

    private Integer price;

    private LocalDateTime time;
}
