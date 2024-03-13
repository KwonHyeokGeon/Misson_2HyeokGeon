package com.example.shopping.domain.user.controller;

import com.example.shopping.domain.user.dto.UserUpdateDto;
import com.example.shopping.domain.user.entity.BusinessRegistration;
import com.example.shopping.domain.user.service.BusinessRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BusinessRegistrationController.REQUEST_PATH)
public class BusinessRegistrationController {

    public final static String REQUEST_PATH = "/business-registrations";

    private final BusinessRegistrationService businessRegistrationService;

    @GetMapping
    public List<BusinessRegistration> readRegisterList() {
        return businessRegistrationService.readBusinessRegistration();
    }

    // 사업자 사용자 신청
    @PutMapping
    public void businessRegister(@RequestBody UserUpdateDto dto) {
        businessRegistrationService.updateBusinessNum(dto);
    }

    @PutMapping("/{id}")
    public void acceptBusinessRegistration(@PathVariable("id")Long id) {
        businessRegistrationService.acceptBusinessRegistration(id);
    }

    @DeleteMapping("/{id}")
    public void declineBusinessRegistration(@PathVariable("id")Long id) {
        businessRegistrationService.declineBusinessRegistration(id);
    }
}
