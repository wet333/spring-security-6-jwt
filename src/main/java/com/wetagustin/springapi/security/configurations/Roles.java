package com.wetagustin.springapi.security.configurations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Roles {

    ROLE_GUEST("GUEST"),
    ROLE_PREMIUM("PREMIUM"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static List<String> asList() {
        return Stream.of(Roles.values())
                .map(Roles::getRole)
                .collect(Collectors.toList());
    }
}
