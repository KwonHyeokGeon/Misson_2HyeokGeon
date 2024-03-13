package com.example.shopping.domain.mall.entity;

import com.example.shopping.domain.mall.entity.enumeration.MallOrderStatus;
import com.example.shopping.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MallOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;
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

    public void accept() {
        this.status = MallOrderStatus.COMPLETE;
        this.mallProduct.setStock(this.count);
    }
}
