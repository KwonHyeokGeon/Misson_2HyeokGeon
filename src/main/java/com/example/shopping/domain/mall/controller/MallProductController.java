package com.example.shopping.domain.mall.controller;

import com.example.shopping.domain.mall.dto.MallProductDto;
import com.example.shopping.domain.mall.entity.MallProduct;
import com.example.shopping.domain.mall.service.MallProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(MallProductController.REQUEST_PATH)
public class MallProductController {
    public final static String REQUEST_PATH = "/mall-products";

    private final MallProductService mallProductService;

    @GetMapping
    public ResponseEntity<List<MallProduct>> readProductByPriceRangeOrName(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String name
    ) {
        List<MallProduct> mallProducts;
        if (minPrice == null && maxPrice == null && name == null) {
            mallProducts = mallProductService.readProduct();
        } else {
            mallProducts = mallProductService.findAllByPriceRangeOrName(minPrice, maxPrice, name);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mallProducts);
    }

    @PostMapping
    public void registerProduct(MallProductDto dto) {
        mallProductService.registerProduct(dto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long id, MallProductDto dto) {
        mallProductService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        mallProductService.deleteProduct(id);
    }
}
