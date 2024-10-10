package com.nbe2.domain.user;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER"),
    MEDICAL_PERSON("ROLE_MEDICAL_PERSON"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    public static UserRole findByRole(final String role) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.role.equals(role))
                .findFirst()
                .orElse(null);
    }
}
