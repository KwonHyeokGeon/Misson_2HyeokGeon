package com.example.shopping.user.entity.enumeration;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum UserAuth {
    DEACTIVE("deactive"),
    NORMAL("normal"),
    BUSINESS("business"),
    ADMIN("admin");

    private final String value;

    UserAuth(String value) {
        this.value = value;
    }

    public static UserAuth fromName(String name) {
        return Stream.of(values()).filter(enumType -> name.equalsIgnoreCase(enumType.name())).findFirst().orElseThrow(() -> new IllegalArgumentException("에러"));

    }
}
