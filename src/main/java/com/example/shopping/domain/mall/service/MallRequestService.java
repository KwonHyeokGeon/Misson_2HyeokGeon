package com.example.shopping.domain.mall.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.mall.dto.MallRequestDto;
import com.example.shopping.domain.mall.dto.MallUpdateDto;
import com.example.shopping.domain.mall.entity.MallRequest;
import com.example.shopping.domain.mall.repository.MallRepository;
import com.example.shopping.domain.mall.repository.MallRequestRepository;
import com.example.shopping.domain.user.entity.User;
import com.example.shopping.domain.user.entity.enumeration.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MallRequestService {

    private final AuthenticationFacade authenticationFacade;
    private final MallRequestRepository mallRequestRepository;
    private final MallRepository mallRepository;

    @Transactional
    public void declineMall(Long id, MallUpdateDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.ADMIN)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        mallRepository.deleteById(id);
        MallRequest build = MallRequest.builder()
                .title(dto.getTitle())
                .explanation(dto.getIntroduce())
                .build();
        mallRequestRepository.save(build);
    }

    @Transactional
    public void closeRegister(MallRequestDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        MallRequest build = MallRequest.builder()
                .title(dto.getTitle())
                .explanation(dto.getExplanation())
                .user(loginUser)
                .build();
        mallRequestRepository.save(build);
    }

    @Transactional
    public void closeMall(Long id) {
        User loginUser = authenticationFacade.getLoginUser();
        if (!loginUser.getAuth().equals(UserAuth.ADMIN)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Optional<MallRequest> byId = mallRequestRepository.findById(id);
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        MallRequest mallRejection = byId.get();
        User user = mallRejection.getUser();
        Long id1 = user.getId();
        mallRepository.deleteByUserId(id1);
    }


}
