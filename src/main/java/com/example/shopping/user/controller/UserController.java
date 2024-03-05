package com.example.shopping.user.controller;

import com.example.shopping.user.dto.UserDto;
import com.example.shopping.user.dto.UserUpdateDto;
import com.example.shopping.user.entity.BusinessRegistration;
import com.example.shopping.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

    // 사용자 정보 추가
    @PutMapping("/update")
    public void update(@RequestBody UserUpdateDto dto) {
        userService.update(dto);
    }

    // 사업자 사용자 신청
    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void profileUpdate(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        Files.createDirectories(Path.of("media"));
        Path path = Path.of("media/" + filename);
        multipartFile.transferTo(path);
        byte[] fileBytes = multipartFile.getBytes();
        userService.updateProfileImage(fileBytes);
    }

    // 사업자 사용자 신청
    @PutMapping("/business")
    public void businessRegister(@RequestBody UserUpdateDto dto) {
        userService.updateBusinessNum(dto);
    }


    @GetMapping("/lists")
    public List<BusinessRegistration> readRegisterList() {
        return userService.readBusinessRegistration();
    }

    @PutMapping("/lists/{id}")
    public void acceptBusinessRegistration(@PathVariable("id")Long id) {
        userService.acceptBusinessRegistration(id);
    }

    @DeleteMapping("/lists/{id}")
    public void declineBusinessRegistration(@PathVariable("id")Long id) {
        userService.declineBusinessRegistration(id);
    }
}
