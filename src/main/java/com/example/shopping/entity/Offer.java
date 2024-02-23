package com.example.shopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Offer {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private OfferStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private UsedProduct usedProduct;
}
