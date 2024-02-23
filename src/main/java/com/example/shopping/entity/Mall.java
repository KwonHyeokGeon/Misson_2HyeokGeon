package com.example.shopping.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Mall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String introduce;
    private MallType mallType;

    @ManyToOne
    private User user;
}
