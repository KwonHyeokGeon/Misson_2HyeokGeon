package com.example.shopping.entity.enumeration;

public enum UsedProductStatus {
    SALE("sale"),
    COMPLETE("complete");

    private final String value;

    UsedProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
