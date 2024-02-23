package com.example.shopping.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class MallProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] image;
    private String explanation;
    private int price;
    private int stock;

    @ManyToOne
    private User user;

    @ManyToOne
    private Mall mall;

}
