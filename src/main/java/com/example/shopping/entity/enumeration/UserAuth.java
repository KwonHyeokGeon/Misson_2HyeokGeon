package com.example.shopping.entity.enumeration;

import org.hibernate.sql.ast.tree.insert.Values;

import java.util.stream.Stream;

public enum UserAuth {
    DEACTIVE("deactive"),
    NORMAL("normal"),
    BUSINESS("business"),
    ADMIN("admin");

    private final String value;

    UserAuth(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserAuth fromName(String name) {
        return Stream.of(values()).filter(enumType -> name.equalsIgnoreCase(enumType.name())).findFirst().orElseThrow(() -> new IllegalArgumentException("에러"));

    }
}
