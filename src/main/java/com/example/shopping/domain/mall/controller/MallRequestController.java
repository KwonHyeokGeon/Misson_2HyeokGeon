package com.example.shopping.domain.mall.controller;


import com.example.shopping.domain.mall.dto.MallRequestDto;
import com.example.shopping.domain.mall.dto.MallUpdateDto;
import com.example.shopping.domain.mall.service.MallRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MallRequestController.REQUEST_PATH)
public class MallRequestController {

    public final static String REQUEST_PATH = "/mall-requests";

    private final MallRequestService mallRequestService;

    @PostMapping
    public void closeRegister(MallRequestDto dto) {
        mallRequestService.closeRegister(dto);
    }

    @PutMapping("/{id}/decline")
    public void decline(@PathVariable("id") Long id, MallUpdateDto dto) {
        mallRequestService.declineMall(id, dto);
    }

    @DeleteMapping("/{id}/closed")
    public void closeMall(@PathVariable("id") Long id) {
        mallRequestService.closeMall(id);
    }
}
