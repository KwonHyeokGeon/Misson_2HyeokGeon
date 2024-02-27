package com.example.shopping.entity;

import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.enumeration.UserAuth;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Entity
@Builder
// 예약어 이슈 회피용
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
    @Setter
    private String businessNum;
    @Enumerated(EnumType.STRING)
    @Setter
    private UserAuth auth = UserAuth.DEACTIVE;
}
