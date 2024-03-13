package com.example.shopping.domain.mall.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.mall.dto.MallOrderAcceptDeclineRequest;
import com.example.shopping.domain.mall.dto.MallOrderRequest;
import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.MallOrder;
import com.example.shopping.domain.mall.entity.MallProduct;
import com.example.shopping.domain.mall.entity.enumeration.MallOrderStatus;
import com.example.shopping.domain.mall.repository.MallOrderRepository;
import com.example.shopping.domain.mall.repository.MallProductRepository;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MallOrderService {

    private final AuthenticationFacade authenticationFacade;
    private final MallOrderRepository mallOrderRepository;
    private final MallProductRepository mallProductRepository;

    @Transactional
    public void orderProduct(MallOrderRequest req) {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long mallProductId = req.getMallProductId();
        Optional<MallProduct> byId = mallProductRepository.findById(mallProductId);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        MallProduct mallProduct = byId.get();

        MallOrder createTarget = MallOrder.builder()
                .count(req.getCount())
                .price(mallProduct.getPrice())
                .paymentTime(LocalDateTime.now())
                .status(MallOrderStatus.STANDBY)
                .user(loginUser)
                .mall(mallProduct.getMall())
                .mallProduct(mallProduct)
                .build();
        mallOrderRepository.save(createTarget);
    }

    @Transactional
    public void declineOrder(MallOrderAcceptDeclineRequest req) {
        Long mallOrderId = req.getMallOrderId();
        Optional<MallOrder> byId = mallOrderRepository.findById(mallOrderId);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        MallOrder mallOrder = byId.get();
        MallOrderStatus status = mallOrder.getStatus();
        if (MallOrderStatus.COMPLETE == status) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        mallOrderRepository.delete(mallOrder);
    }

    @Transactional
    public void acceptOrder(MallOrderAcceptDeclineRequest req) {
        User loginUser = authenticationFacade.getLoginUser();
        Long mallOrderId = req.getMallOrderId();
        Optional<MallOrder> byId = mallOrderRepository.findById(mallOrderId);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        MallOrder mallOrder = byId.get();
        Mall mall = mallOrder.getMall();
        User user = mall.getUser();
        if (!loginUser.equals(user)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        mallOrder.accept();
    }

}
