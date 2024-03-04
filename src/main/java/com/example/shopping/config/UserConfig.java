package com.example.shopping.config;

import com.example.shopping.entity.User;
import com.example.shopping.entity.enumeration.UserAuth;
import com.example.shopping.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
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
        User user = User.builder()
                .userId("testUser")
                .password(passwordEncoder.encode("1111"))
                .age("20")
                .phone("1234")
                .name("hikari")
                .auth(UserAuth.NORMAL)
                .nickname("hikari")
                .build();
        userRepository.save(user);
    }

    @PostConstruct
    public void businessUser() {
        User user = User.builder()
                .userId("testUser2")
                .password(passwordEncoder.encode("1111"))
                .age("20")
                .phone("1234")
                .name("business")
                .auth(UserAuth.BUSINESS)
                .nickname("business")
                .build();
        userRepository.save(user);
    }


}
