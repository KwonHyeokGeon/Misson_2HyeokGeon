package com.example.shopping.controller;

import com.example.shopping.dto.*;
import com.example.shopping.entity.Mall;
import com.example.shopping.entity.MallProduct;
import com.example.shopping.entity.enumeration.MallType;
import com.example.shopping.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mall")
@RequiredArgsConstructor
public class MallController {
    private final MallService mallService;

    @PutMapping("/{id}/update")
    public void update(@PathVariable("id") Long id, MallUpdateDto dto) {
        mallService.updateMall(id, dto);
    }

    @PutMapping("/{id}/accept")
    public void accept(@PathVariable("id") Long id) {
        mallService.acceptMall(id);
    }

    @PutMapping("/{id}/decline")
    public void decline(@PathVariable("id") Long id, MallUpdateDto dto) {
        mallService.declineMall(id, dto);
    }

    @PostMapping("/closed-register")
    public void closeRegister(MallRequestDto dto) {
        mallService.closeRegister(dto);
    }

    @DeleteMapping("/{id}/closed")
    public void closeMall(@PathVariable("id") Long id) {
        mallService.closeMall(id);
    }

    @PostMapping("/{id}/product")
    public void registerProduct(MallProductDto dto) {
        mallService.registerProduct(dto);
    }

    @PutMapping("/{id}/product/{productId}")
    public void updateProduct(@PathVariable("productId") Long productId, MallProductDto dto) {
        mallService.updateProduct(productId, dto);
    }

    @DeleteMapping("/{id}/product/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        mallService.deleteProduct(productId);
    }

    @GetMapping
    public ResponseEntity<List<Mall>> readAll() {
        List<Mall> malls = mallService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(malls);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Mall>> readByTitleOrMallType(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MallType mallType
    ) {
        List<Mall> malls = mallService.readByTitleOrMallType(title, mallType);
        return ResponseEntity.status(HttpStatus.OK).body(malls);
    }

    @GetMapping("/products")
    public ResponseEntity<List<MallProduct>> readProduct() {
        List<MallProduct> mallProducts = mallService.readProduct();
        return ResponseEntity.status(HttpStatus.OK).body(mallProducts);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<MallProduct>> readProductByPriceRangeOrName(
            @RequestParam(required = false) int minPrice,
            @RequestParam(required = false) int maxPrice,
            @RequestParam(required = false) String name
    ) {
        List<MallProduct> allByPriceRangeOrName = mallService.findAllByPriceRangeOrName(minPrice, maxPrice, name);
        return ResponseEntity.status(HttpStatus.OK).body(allByPriceRangeOrName);
    }

    @PostMapping("/mall-orders")
    public void orderProduct(
            @Validated @RequestBody MallOrderRequest req
    ) {
        mallService.orderProduct(req);
    }

    @PatchMapping("/mall-orders/accept")
    public void acceptOrder(
            @Validated @RequestBody MallOrderAcceptDeclineRequest req
    ) {
        mallService.acceptOrder(req);
    }

    @DeleteMapping("/mall-orders/decline")
    public void declineOrder(
            @Validated @RequestBody MallOrderAcceptDeclineRequest req
    ) {
        mallService.declineOrder(req);
    }
}
