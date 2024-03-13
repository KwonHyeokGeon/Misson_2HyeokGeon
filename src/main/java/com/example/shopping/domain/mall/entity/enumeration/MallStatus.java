package com.example.shopping.domain.mall.entity.enumeration;

import lombok.Getter;

@Getter
public enum MallStatus {
    PREPARING("준비중"),
    OPEN("오픈");

    private final String value;

    MallStatus(String value) {
        this.value = value;
    }
}
