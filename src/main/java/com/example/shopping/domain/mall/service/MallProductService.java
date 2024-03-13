package com.example.shopping.domain.mall.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.mall.dto.MallProductDto;
import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.MallProduct;
import com.example.shopping.domain.mall.repository.MallProductRepository;
import com.example.shopping.domain.mall.repository.MallRepository;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
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
public class MallProductService {

    private final AuthenticationFacade authenticationFacade;
    private final MallProductRepository mallProductRepository;
    private final MallRepository mallRepository;

    @Transactional
    public void registerProduct(MallProductDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        Long id = loginUser.getId();
        Mall byUserId = mallRepository.findByUserId(id);
        MallProduct build = MallProduct.builder()
                .name(dto.getName())
                .image(dto.getImage())
                .explanation(dto.getExplanation())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .mall(byUserId)
                .build();
        mallProductRepository.save(build);
    }

    @Transactional
    public void updateProduct(Long id, MallProductDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        Optional<MallProduct> byId = mallProductRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        User user = byId.get().getMall().getUser();
        if (!user.equals(loginUser)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        MallProduct mallProduct = byId.get();
        mallProduct.setName(dto.getName());
        mallProduct.setImage(dto.getImage());
        mallProduct.setExplanation(dto.getExplanation());
        mallProduct.setPrice(dto.getPrice());
        mallProduct.setStock(dto.getStock());
    }

    @Transactional
    public void deleteProduct(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        Optional<MallProduct> byId = mallProductRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        User user = byId.get().getMall().getUser();
        if (!user.equals(loginUser)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        mallProductRepository.deleteById(id);
    }


    public List<MallProduct> readProduct() {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        List<MallProduct> all = mallProductRepository.findAll();
        return all;
    }

    public List<MallProduct> findAllByPriceRangeOrName(Integer minPrice, Integer maxPrice, String name) {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return mallProductRepository.findByPriceBetweenOrNameIgnoreCase(minPrice, maxPrice, name);
    }

}
