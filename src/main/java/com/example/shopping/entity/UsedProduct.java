package com.example.shopping.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UsedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String explanation;
    @Lob
    private byte[] thumbnail;
    private int minPrice;
    @Enumerated(EnumType.STRING)
    private UsedProductStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
