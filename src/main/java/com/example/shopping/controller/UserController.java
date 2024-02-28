package com.example.shopping.controller;

import com.example.shopping.dto.UserDto;
import com.example.shopping.dto.UserUpdateDto;
import com.example.shopping.entity.User;
import com.example.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public String testLogin(@PathVariable("id")Long id) {
        return "done";
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 사용자 정보 추가
    @PutMapping("/{id}/update")
    public void update(@RequestBody UserUpdateDto dto, @PathVariable("id") Long id) {
        userService.update(dto, id);
    }

    // 사업자 사용자 신청
    @PutMapping(value = "/{id}/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void profileUpdate(@RequestParam("image") MultipartFile multipartFile, @PathVariable("id") Long id) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        Files.createDirectories(Path.of("media"));
        Path path = Path.of("media/" + filename);
        multipartFile.transferTo(path);
        byte[] fileBytes = multipartFile.getBytes();
        userService.updateProfileImage(fileBytes, id);
    }

    // 사업자 사용자 신청
    @PutMapping("/{id}/business")
    public void businessRegister(@RequestBody UserUpdateDto dto, @PathVariable("id") Long id) {
        userService.updateBusinessNum(dto, id);
    }




/*    @PostMapping("/signin")
    public ResponseEntity signIn() {

    }*/
}
