package com.example.shopping.ncp;

import org.springframework.beans.factory.annotation.Value;

public class CaptchaService {
    private static final String CAPTCHA_API_URL = "https://openapi.naver.com/v1/captcha/nkey";

    @Value("${ncp.api.client-id}")
    private String CLIENT_ID;
    @Value("${ncp.api.client-secret}")
    private String CLIENT_SECRET;
}
