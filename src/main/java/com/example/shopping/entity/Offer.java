package com.example.shopping.entity;

import com.example.shopping.entity.enumeration.OfferStatus;
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
