package com.example.shopping.domain.mall.service;

import com.example.shopping.common.jwt.AuthenticationFacade;
import com.example.shopping.domain.mall.dto.MallUpdateDto;
import com.example.shopping.domain.mall.entity.Mall;
import com.example.shopping.domain.mall.entity.enumeration.MallStatus;
import com.example.shopping.domain.mall.entity.enumeration.MallType;
import com.example.shopping.domain.mall.repository.MallRepository;
import com.example.shopping.domain.mall.repository.MallRequestRepository;
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
public class MallService {
    private final MallRepository mallRepository;
    private final MallRequestRepository mallRequestRepository;
    private final AuthenticationFacade authenticationFacade;

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


}
