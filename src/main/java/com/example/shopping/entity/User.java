package com.example.shopping.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Arrays;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private String name;
    private String nickname;
    private String age;
    private String phone;
    @Lob
    private byte[] profileImage;
    @Enumerated(EnumType.STRING)
    private UserAuth auth;
}
