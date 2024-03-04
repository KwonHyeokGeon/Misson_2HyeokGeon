package com.example.shopping.service;

import com.example.shopping.dto.MallOrderDto;
import com.example.shopping.dto.MallProductDto;
import com.example.shopping.dto.MallRequestDto;
import com.example.shopping.dto.MallUpdateDto;
import com.example.shopping.entity.Mall;
import com.example.shopping.entity.MallProduct;
import com.example.shopping.entity.MallRequest;
import com.example.shopping.entity.User;
import com.example.shopping.entity.enumeration.MallStatus;
import com.example.shopping.entity.enumeration.MallType;
import com.example.shopping.entity.enumeration.UserAuth;
import com.example.shopping.jwt.AuthenticationFacade;
import com.example.shopping.repository.MallProductRepository;
import com.example.shopping.repository.MallRejectionRepository;
import com.example.shopping.repository.MallRepository;
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
public class MallService {
    private final MallRepository mallRepository;
    private final MallRejectionRepository mallRejectionRepository;
    private final AuthenticationFacade authenticationFacade;
    private final MallProductRepository mallProductRepository;

    @Transactional
    public void updateMall(Long id, MallUpdateDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        Optional<Mall> byId = mallRepository.findById(id);
        if (byId.isEmpty() || !byId.get().getUser().equals(loginUser))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Mall mall = byId.get();
        mall.setTitle(dto.getTitle());
        mall.setIntroduce(dto.getIntroduce());
        mall.setMallType(dto.getMalltype());
    }

    @Transactional
    public void acceptMall(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.ADMIN)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Optional<Mall> byId = mallRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byId.get().setMallStatus(MallStatus.OPEN);
    }

    @Transactional
    public void declineMall(Long id, MallUpdateDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.ADMIN)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        mallRepository.deleteById(id);
        MallRequest build = MallRequest.builder().title(dto.getTitle()).explanation(dto.getIntroduce()).build();
        mallRejectionRepository.save(build);
    }

    @Transactional
    public void closeRegister(MallRequestDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        MallRequest build = MallRequest.builder()
                .title(dto.getTitle())
                .explanation(dto.getExplanation())
                .user(loginUser)
                .build();

        mallRejectionRepository.save(build);
    }

    @Transactional
    public void closeMall(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.ADMIN)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Optional<MallRequest> byId = mallRejectionRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        MallRequest mallRejection = byId.get();
        User user = mallRejection.getUser();
        Long id1 = user.getId();
        mallRepository.deleteByUserId(id1);
    }

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

    public List<Mall> readAll() {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return mallRepository.findAll();
    }

    public List<Mall> readByTitleOrMallType(String title, MallType mallType) {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        List<Mall> byTitle = mallRepository.findByMallTypeOrTitle(mallType, title);
        return byTitle;
    }

//    public List<Mall> readByMallType() {
//        User loginUser = authenticationFacade.getLoginUser();
//        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        List<Mall> byMallType = mallRepository.findByMallType(mallType);
//        return byMallType;
//    }

    public List<MallProduct> readProduct() {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        List<MallProduct> all = mallProductRepository.findAll();
        return all;
    }

    public List<MallProduct> findAllByPriceRangeOrName(int minPrice, int maxPrice, String name) {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return mallProductRepository.findByPriceBetweenOrNameIgnoreCase(minPrice, maxPrice, name);
    }

    public void orderProduct(MallOrderDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        if (loginUser.getAuth().equals(UserAuth.DEACTIVE)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);


    }
}
