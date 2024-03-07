package com.example.shopping.common.jwt;

import com.example.shopping.common.jwt.entity.CustomUserDetails;
import com.example.shopping.user.entity.User;
import com.example.shopping.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final UserRepository userRepository;
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        CustomUserDetails principal1 = (CustomUserDetails) principal;
        String userId = principal1.getUserId();
        User byUserId = userRepository.findByUserId(userId);
        return byUserId;
    }
}
