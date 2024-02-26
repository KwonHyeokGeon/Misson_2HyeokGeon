package com.example.shopping.entity.enumeration;

import org.hibernate.sql.ast.tree.insert.Values;

import java.util.stream.Stream;

public enum UserAuth {
    DEACTIVE,
    NORMAL,
    BUSINESS,
    ADMIN;

    public static UserAuth fromName(String name) {
        return Stream.of(values()).filter(enumType -> name.equalsIgnoreCase(enumType.name())).findFirst().orElseThrow(() -> new IllegalArgumentException("에러"));

    }
}
