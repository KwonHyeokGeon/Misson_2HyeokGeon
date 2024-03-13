package com.example.shopping.domain.mall.controller;

import com.example.shopping.domain.mall.dto.MallUpdateDto;
import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.enumeration.MallType;
import com.example.shopping.domain.mall.service.MallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(MallController.REQUEST_PATH)
public class MallController {
    public final static String REQUEST_PATH = "/malls";

    private final MallService mallService;

    @GetMapping
    public ResponseEntity<List<Mall>> readAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MallType mallType
    ) {
        List<Mall> malls;
        if (title == null && mallType == null) {
            malls = mallService.readAll();
        } else {
            malls = mallService.readByTitleOrMallType(title, mallType);
        }
        return ResponseEntity.status(HttpStatus.OK).body(malls);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, MallUpdateDto dto) {
        mallService.updateMall(id, dto);
    }

    @PutMapping("/{id}/accept")
    public void accept(@PathVariable("id") Long id) {
        mallService.acceptMall(id);
    }
}
