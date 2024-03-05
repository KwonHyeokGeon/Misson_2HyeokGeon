package com.example.shopping.config;

import com.example.shopping.user.entity.User;
import com.example.shopping.user.entity.enumeration.UserAuth;
import com.example.shopping.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserInitConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 관리자 계정 생성
    @PostConstruct
    public void adminUser() {
        User admin = User.builder()
                .userId("admin")
                .password(passwordEncoder.encode("1111"))
                .auth(UserAuth.ADMIN)
                .build();
        userRepository.save(admin);
    }


    @PostConstruct
    public void normalUser() {
        User user1 = User.builder()
                .userId("testUser")
                .password(passwordEncoder.encode("1111"))
                .age("20")
                .phone("1234")
                .name("hikari")
                .auth(UserAuth.NORMAL)
                .nickname("hikari")
                .build();

        User user2 = User.builder()
                .userId("normalUser")
                .password(passwordEncoder.encode("1111"))
                .age("20")
                .phone("1234")
                .name("hikari")
                .auth(UserAuth.NORMAL)
                .nickname("hikari")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
    }


    @PostConstruct
    public void businessUser() {
        User user = User.builder()
                .userId("testUser2")
                .password(passwordEncoder.encode("1111"))
                .age("25")
                .phone("1234")
                .name("business")
                .auth(UserAuth.BUSINESS)
                .nickname("business")
                .build();
        userRepository.save(user);
    }


}
