package com.example.shopping.controller;

import com.example.shopping.dto.UsedProductDto;
import com.example.shopping.entity.UsedProduct;
import com.example.shopping.service.UsedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class UsedProductController {
    private final UsedProductService usedProductService;

    @PostMapping("/new")
    public void register(@RequestBody UsedProductDto dto) {
        usedProductService.register(dto);
    }
//    @PostMapping(value = "/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public void register(@RequestBody UsedProductDto dto, @RequestParam("image") MultipartFile multipartFile) throws IOException {
//        String filename = multipartFile.getOriginalFilename();
//        Files.createDirectories(Path.of("media/usedProduct"));
//        Path path = Path.of("media/usedProduct" + filename);
//        multipartFile.transferTo(path);
//        byte[] fileBytes = multipartFile.getBytes();
//        usedProductService.register(dto, fileBytes);
//    }

    @GetMapping("/lists")
    public List<UsedProduct> readAll() {
        return usedProductService.readAll();
    }

    @PutMapping("/{id}")
    public void updateUsedProduct(@PathVariable("id") Long id, UsedProductDto dto) {
        usedProductService.updateUsedProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUsedProduct(@PathVariable("id") Long id) {
        usedProductService.deleteUsedProduct(id);
    }


}
