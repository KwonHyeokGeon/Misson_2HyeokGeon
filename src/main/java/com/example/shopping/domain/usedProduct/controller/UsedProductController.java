package com.example.shopping.domain.usedProduct.controller;

import com.example.shopping.domain.usedProduct.dto.OfferCreateRequest;
import com.example.shopping.domain.usedProduct.dto.UsedProductDto;
import com.example.shopping.domain.usedProduct.entity.Offer;
import com.example.shopping.domain.usedProduct.entity.UsedProduct;
import com.example.shopping.domain.usedProduct.service.UsedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void updateUsedProduct(@PathVariable("id") Long id, @RequestBody UsedProductDto dto) {
        usedProductService.updateUsedProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUsedProduct(@PathVariable("id") Long id) {
        usedProductService.deleteUsedProduct(id);
    }

    @PostMapping("/offers")
    public void offerProduct(
            @Validated @RequestBody OfferCreateRequest req
    ) {
        usedProductService.productProposal(req);
    }

    @PatchMapping("/offers/{id}/accept")
    public void offerAccept(@PathVariable("id") Long id) {
        usedProductService.offerAccept(id);
    }

    @PatchMapping("/offers/{id}/decline")
    public void offerDecline(@PathVariable("id") Long id) {
        usedProductService.offerDecline(id);
    }

    @PatchMapping("/offers/{id}/complete")
    public void offerComplete(@PathVariable("id") Long id) {
        usedProductService.offerComplete(id);
    }

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> readOfferAll() {
        List<Offer> offers = usedProductService.readOffer();
        ResponseEntity<List<Offer>> response = ResponseEntity.status(HttpStatus.OK).body(offers);
        return response;
    }
}

