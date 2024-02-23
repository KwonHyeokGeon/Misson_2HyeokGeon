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
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String age;
    private String phone;
    @Lob // Large Object annotation for handling large data like images
    private byte[] profileImage;
    private UserAuth auth;
}
