package com.example.shopping.dto;

import com.example.shopping.entity.*;
import com.example.shopping.entity.enumeration.MallOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MallOrderDto {
    private Long id;
    private int stock;
    private int price;
    private LocalDateTime paymentTime;
    private MallOrderStatus status;
    private User user;
    private Mall mall;
    private MallProduct mallProduct;

    public static MallOrderDto fromEntity(MallOrder mallOrder) {
        return MallOrderDto.builder()
                .id(mallOrder.getId())
                .stock(mallOrder.getStock())
                .price(mallOrder.getPrice())
                .paymentTime(mallOrder.getPaymentTime())
                .status(mallOrder.getStatus())
                .user(mallOrder.getUser())
                .mall(mallOrder.getMall())
                .mallProduct(mallOrder.getMallProduct())
                .build();

    }
}
