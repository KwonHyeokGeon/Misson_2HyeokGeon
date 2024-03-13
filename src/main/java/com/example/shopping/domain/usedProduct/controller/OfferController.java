package com.example.shopping.domain.usedProduct.controller;

import com.example.shopping.domain.usedProduct.dto.OfferCreateRequest;
import com.example.shopping.domain.usedProduct.entity.Offer;
import com.example.shopping.domain.usedproduct.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(OfferController.REQUEST_PATH)
@RequiredArgsConstructor
public class OfferController {
    public final static String REQUEST_PATH = "/offers";

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<List<Offer>> readOfferAll() {
        List<Offer> offers = offerService.readOffer();
        return ResponseEntity.status(HttpStatus.OK).body(offers);
    }

    @PostMapping
    public void offerProduct(
            @Validated @RequestBody OfferCreateRequest req
    ) {
        offerService.productProposal(req);
    }

    @PatchMapping("/{id}/accept")
    public void offerAccept(@PathVariable("id") Long id) {
        offerService.offerAccept(id);
    }

    @PatchMapping("/{id}/decline")
    public void offerDecline(@PathVariable("id") Long id) {
        offerService.offerDecline(id);
    }

    @PatchMapping("/{id}/complete")
    public void offerComplete(@PathVariable("id") Long id) {
        offerService.offerComplete(id);
    }

}
