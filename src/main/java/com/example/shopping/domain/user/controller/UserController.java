package com.example.shopping.domain.user.controller;

import com.example.shopping.domain.user.dto.UserDto;
import com.example.shopping.domain.user.dto.UserUpdateDto;
import com.example.shopping.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.REQUEST_PATH)
public class UserController {

    public final static String REQUEST_PATH = "/users";

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 사용자 정보 추가
    @PutMapping
    public void update(@RequestBody UserUpdateDto dto) {
        userService.update(dto);
    }

    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void profileUpdate(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        Files.createDirectories(Path.of("media"));
        Path path = Path.of("media/" + filename);
        multipartFile.transferTo(path);
        byte[] fileBytes = multipartFile.getBytes();
        userService.updateProfileImage(fileBytes);
    }
}
