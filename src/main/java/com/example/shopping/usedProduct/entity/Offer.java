package com.example.shopping.usedProduct.entity;

import com.example.shopping.user.entity.User;
import com.example.shopping.usedProduct.entity.enumeration.OfferStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    @Setter
    private OfferStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private UsedProduct usedProduct;
}
