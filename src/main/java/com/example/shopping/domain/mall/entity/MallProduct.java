package com.example.shopping.domain.mall.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MallProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Lob
    @Setter
    private byte[] image;
    @Setter
    private String explanation;
    @Setter
    private int price;
    @Setter
    private int stock;


    @ManyToOne
    private Mall mall;

}
