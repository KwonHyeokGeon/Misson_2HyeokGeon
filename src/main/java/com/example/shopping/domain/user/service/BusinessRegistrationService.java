package com.example.shopping.domain.user.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.enumeration.MallStatus;
import com.example.shopping.domain.mall.repository.MallRepository;
import com.example.shopping.domain.user.dto.UserUpdateDto;
import com.example.shopping.domain.user.entity.BusinessRegistration;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
import com.example.shopping.domain.user.repository.BusinessRepository;
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
public class BusinessRegistrationService {

    private final AuthenticationFacade authenticationFacade;
    private final BusinessRepository businessRepository;
    private final MallRepository mallRepository;

    // 사업자 사용자 신청
    @Transactional
    public void updateBusinessNum(UserUpdateDto dto) {
        User findUser = authenticationFacade.getLoginUser();
        // 일반 사용자 일때
        if (!findUser.getAuth().equals(UserAuth.NORMAL)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        BusinessRegistration build = BusinessRegistration.builder().
                user(findUser).
                businessNum(dto.getBusinessNum()).
                build();

        businessRepository.save(build);
    }

    // 사업자 사용자 신청 목록
    public List<BusinessRegistration> readBusinessRegistration() {
        User findUser = authenticationFacade.getLoginUser();
        if (!findUser.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return businessRepository.findAll();
    }

    // 사업자 사용자 신청 수락
    @Transactional
    public void acceptBusinessRegistration(Long id) {

        User user = authenticationFacade.getLoginUser();
        if (!user.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Optional<BusinessRegistration> byId = businessRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user1 = byId.get().getUser();
        user1.setAuth(UserAuth.BUSINESS);
        // 준비 중 상태의 쇼핑몰 추가
        Mall build = Mall.builder().mallStatus(MallStatus.PREPARING).user(user1).build();

        mallRepository.save(build);

    }

    @Transactional
    public void declineBusinessRegistration(Long id) {
        User user = authenticationFacade.getLoginUser();
        if (!user.getAuth().equals(UserAuth.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Optional<BusinessRegistration> byId = businessRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        businessRepository.deleteById(id);
    }

}
