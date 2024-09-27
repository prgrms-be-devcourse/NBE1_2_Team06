package com.nbe2.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER"),
    MEDICAL_PERSON("ROLE_MEDICAL_PERSON"),
    ADMIN("ROLE_ADMIN");

    private final String role;
}
