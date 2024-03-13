package com.example.shopping.domain.usedproduct.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.usedProduct.dto.OfferCreateRequest;
import com.example.shopping.domain.usedProduct.entity.Offer;
import com.example.shopping.domain.usedProduct.entity.UsedProduct;
import com.example.shopping.domain.usedProduct.entity.enumeration.OfferStatus;
import com.example.shopping.domain.usedProduct.entity.enumeration.UsedProductStatus;
import com.example.shopping.domain.usedProduct.repository.OfferRepository;
import com.example.shopping.domain.usedProduct.repository.UsedProductRepository;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfferService {

    private final AuthenticationFacade authenticationFacade;
    private final OfferRepository offerRepository;
    private final UsedProductRepository usedProductRepository;

    @Transactional
    public void productProposal(OfferCreateRequest req) {
        // 로그인한 사용자
        User loginUser = authenticationFacade.getLoginUser();
        // 저장된 중고상품
        Long usedProductId = req.getUsedProductId();
        Optional<UsedProduct> byId = usedProductRepository.findById(usedProductId);
        // 중고상품 확인
        if (byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UsedProduct usedProduct = byId.get();
        // 중고상품등록한 사용자
        User user = usedProduct.getUser();
        // 로그인한 사람이 등록한 사용자 인지 아닌지
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE) || user.equals(loginUser))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Offer createTarget = Offer.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .status(OfferStatus.STANDBY)
                .user(loginUser)
                .usedProduct(usedProduct)
                .build();

        offerRepository.save(createTarget);
    }

    @Transactional
    public void offerAccept(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = offer.getUsedProduct().getUser();
        if (!user.equals(loginUser)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        OfferStatus status = offer.getStatus();
        // 구매 제안이 대기 상태가 아니라면 오류처리
        if (!status.equals(OfferStatus.STANDBY)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        offer.setStatus(OfferStatus.AGREE);
    }

    @Transactional
    public void offerDecline(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = offer.getUsedProduct().getUser();
        if (!user.equals(loginUser)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        OfferStatus status = offer.getStatus();
        // 구매 제안이 대기 상태가 아니라면 오류처리
        if (!status.equals(OfferStatus.STANDBY)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        offer.setStatus(OfferStatus.REJECT);
    }

    @Transactional
    public void offerComplete(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        User user = offer.getUser();
        if (!user.equals(loginUser)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        if (!offer.getStatus().equals(OfferStatus.AGREE)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        offer.setStatus(OfferStatus.CONFIRM);


        UsedProduct usedProduct = offer.getUsedProduct();
        usedProduct.setStatus(UsedProductStatus.COMPLETE);

        List<Offer> offerList = usedProduct.getOfferList();
        for (Offer find : offerList) {
            if (!find.getId().equals(offer.getId())) {
                find.setStatus(OfferStatus.REJECT);
            }
        }
    }

    public List<Offer> readOffer() {
        User loginUser = authenticationFacade.getLoginUser();
        List<Offer> collect = offerRepository.findAll().stream().filter(offer -> {
            if (offer.getUser().equals(loginUser)) return true;
            if (offer.getUsedProduct().getUser().equals(loginUser)) return true;
            return false;
        }).collect(Collectors.toList());

        return collect;

    }

}
