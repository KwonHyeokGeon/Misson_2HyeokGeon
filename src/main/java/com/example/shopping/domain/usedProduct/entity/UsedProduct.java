package com.example.shopping.domain.usedProduct.entity;

import com.example.shopping.domain.usedProduct.entity.enumeration.UsedProductStatus;
import com.example.shopping.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UsedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Setter
    private String title;
    @Column(nullable = false)
    @Setter
    private String explanation;
    @Lob
    @Setter
    private byte[] thumbnail;
    @Column(nullable = false)
    @Setter
    private int minPrice;
    @Enumerated(EnumType.STRING)
    @Setter
    private UsedProductStatus status = UsedProductStatus.SALE;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "usedProduct")
    private List<Offer> offerList;
}
