package com.example.shopping.entity;

import com.example.shopping.entity.enumeration.OfferStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

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
