package com.example.shopping.service;

import com.example.shopping.dto.UsedProductDto;
import com.example.shopping.entity.UsedProduct;
import com.example.shopping.entity.User;
import com.example.shopping.entity.enumeration.UsedProductStatus;
import com.example.shopping.entity.enumeration.UserAuth;
import com.example.shopping.jwt.AuthenticationFacade;
import com.example.shopping.repository.UsedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsedProductService {
    private final UsedProductRepository usedProductRepository;
    private final AuthenticationFacade authenticationFacade;
    @Transactional
    public void register(UsedProductDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.NORMAL)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UsedProduct usedProduct = UsedProduct.builder()
                .title(dto.getTitle())
                .explanation(dto.getExplanation())
                .minPrice(dto.getMinPrice())
                .thumbnail(dto.getThumbnail())
                .status(UsedProductStatus.SALE)
                .user(loginUser)
                .build();
        usedProductRepository.save(usedProduct);
    }
    // Content-type 달라서 요청 2개 만들 지 고민 중
//    public void register(UsedProductDto dto, byte[] fileBytes) {
//        User loginUser = authenticationFacade.getLoginUser();
//        if (!loginUser.getAuth().equals(UserAuth.NORMAL)) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
//
//        UsedProduct usedProduct = UsedProduct.builder()
//                .title(dto.getTitle())
//                .explanation(dto.getExplanation())
//                .minPrice(dto.getMinPrice())
//                .thumbnail(fileBytes)
//                .status(UsedProductStatus.SALE)
//                .user(loginUser)
//                .build();
//        usedProductRepository.save(usedProduct);
//    }

    public List<UsedProduct> readAll() {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return usedProductRepository.findAll();
    }

    @Transactional
    public void updateUsedProduct(Long id, UsedProductDto dto) {
        Optional<UsedProduct> byId = usedProductRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UsedProduct usedProduct = byId.get();
        User loginUser = authenticationFacade.getLoginUser();
        User user = usedProduct.getUser();
        if (!user.equals(loginUser)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        usedProduct.setTitle(dto.getTitle());
        usedProduct.setExplanation(dto.getExplanation());
        usedProduct.setMinPrice(dto.getMinPrice());
    }

    @Transactional
    public void deleteUsedProduct(Long id) {
        Optional<UsedProduct> byId = usedProductRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User user = byId.get().getUser();
        User loginUser = authenticationFacade.getLoginUser();

        if (!user.equals(loginUser)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        usedProductRepository.deleteById(id);
    }

    public void productProposal(Long id) {
        // 로그인한 사용자
        User loginUser = authenticationFacade.getLoginUser();
        // 저장된 중고상품
        Optional<UsedProduct> byId = usedProductRepository.findById(id);
        // 중고상품 확인
        if (byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 중고상품등록한 사용자
        User user = byId.get().getUser();
        // 로그인한 사람이 등록한 사용자 인지 아닌지
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE) || user.equals(loginUser))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    }
}
