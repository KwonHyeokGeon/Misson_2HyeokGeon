package com.example.shopping.controller;

import com.example.shopping.dto.UserDto;
import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserUpdateDto dto, @PathVariable("id") Long id) {
        userService.update(dto, id);
    }


}
