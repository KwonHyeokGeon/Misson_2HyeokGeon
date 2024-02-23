package com.example.shopping.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class MallOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stock;
    private int price;
    private LocalDateTime paymentTime;
    private MallOrderStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Mall mall;

    @ManyToOne
    private MallProduct mallProduct;
}
