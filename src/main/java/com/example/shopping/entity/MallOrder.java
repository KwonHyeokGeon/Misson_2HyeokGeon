package com.example.shopping.entity;

import com.example.shopping.entity.enumeration.MallOrderStatus;
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
    @Enumerated(EnumType.STRING)
    private MallOrderStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Mall mall;

    @ManyToOne
    private MallProduct mallProduct;
}
