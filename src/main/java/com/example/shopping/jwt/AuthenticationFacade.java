package com.example.shopping.jwt;

import com.example.shopping.entity.CustomUserDetails;
import com.example.shopping.service.JpaUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final JpaUserDetailsManager userDetailsManager;
    public CustomUserDetails loginUserDetails() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return (CustomUserDetails) userDetailsManager.loadUserByUsername(name);
    }
}
