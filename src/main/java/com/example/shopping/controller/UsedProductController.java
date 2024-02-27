package com.example.shopping.controller;

import com.example.shopping.dto.UsedProductDto;
import com.example.shopping.service.UsedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class UsedProductController {
    private final UsedProductService usedProductService;

    @PostMapping("/new")
    public void register(@RequestBody UsedProductDto dto) {
        usedProductService.register(dto);
    }

}
