package com.nbe2.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER"),
    MEDICAL_PERSON("MEDICAL_PERSON"),
    ADMIN("ADMIN");

    private final String role;
}
