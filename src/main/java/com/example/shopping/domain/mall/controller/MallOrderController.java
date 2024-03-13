package com.example.shopping.domain.mall.controller;

import com.example.shopping.domain.mall.dto.MallOrderAcceptDeclineRequest;
import com.example.shopping.domain.mall.dto.MallOrderRequest;
import com.example.shopping.domain.mall.service.MallOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MallOrderController.REQUEST_PATH)
public class MallOrderController {
    public final static String REQUEST_PATH = "/mall-orders";

    private final MallOrderService mallOrderService;

    @PostMapping
    public void orderProduct(
            @Validated @RequestBody MallOrderRequest req
    ) {
        mallOrderService.orderProduct(req);
    }

    @PatchMapping("/accept")
    public void acceptOrder(
            @Validated @RequestBody MallOrderAcceptDeclineRequest req
    ) {
        mallOrderService.acceptOrder(req);
    }

    @DeleteMapping
    public void declineOrder(
            @Validated @RequestBody MallOrderAcceptDeclineRequest req
    ) {
        mallOrderService.declineOrder(req);
    }

}
