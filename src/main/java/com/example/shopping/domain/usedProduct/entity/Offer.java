package com.example.shopping.domain.usedProduct.entity;

import com.example.shopping.domain.usedProduct.entity.enumeration.OfferStatus;
import com.example.shopping.domain.user.entity.User;
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
