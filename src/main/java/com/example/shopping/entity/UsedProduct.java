package com.example.shopping.entity;

import com.example.shopping.entity.enumeration.UsedProductStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UsedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String explanation;
    @Lob
    private byte[] thumbnail;
    @Column(nullable = false)
    private int minPrice;
    @Enumerated(EnumType.STRING)
    private UsedProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
