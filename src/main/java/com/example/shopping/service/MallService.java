package com.example.shopping.service;

import com.example.shopping.dto.MallUpdateDto;
import com.example.shopping.entity.MallRegistration;
import com.example.shopping.entity.User;
import com.example.shopping.jwt.AuthenticationFacade;
import com.example.shopping.repository.MallRegisterRepository;
import com.example.shopping.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MallService {
    private final MallRepository mallRepository;
    private final MallRegisterRepository mallRegisterRepository;
    private final AuthenticationFacade authenticationFacade;

    public void updateMall(Long id, MallUpdateDto dto) {
        User loginUser = authenticationFacade.getLoginUser();
        Optional<MallRegistration> byId = mallRegisterRepository.findById(id);
        if (byId.isEmpty() || !byId.get().getUser().equals(loginUser)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        MallRegistration mallRegistration = byId.get();
        mallRegistration.setTitle(dto.getTitle());
        mallRegistration.setIntroduce(dto.getIntroduce());
        mallRegistration.setMallType(dto.getMallType());
    }
}
