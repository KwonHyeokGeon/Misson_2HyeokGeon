package com.example.shopping.entity.enumeration;

import lombok.Getter;

@Getter
public enum MallType {
    CLOTHES("의류"),
    FOOD("식품"),
    ELECTRONICS("전자제품")

    ;

    private final String displayValue;

    MallType(String displayValue) {
        this.displayValue = displayValue;
    }

}
