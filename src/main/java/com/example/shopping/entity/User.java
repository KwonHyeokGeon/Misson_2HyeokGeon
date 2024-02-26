package com.example.shopping.entity;

import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.enumeration.UserAuth;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Setter
    private String name;
    @Setter
    private String nickname;
    @Setter
    private String age;
    @Setter
    private String phone;
    @Lob
    @Setter
    private byte[] profileImage;
    @Enumerated(EnumType.STRING)
    private UserAuth auth;

}
