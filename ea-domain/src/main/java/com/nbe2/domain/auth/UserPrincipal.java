package com.nbe2.domain.auth;

import com.nbe2.domain.user.UserRole;

public record UserPrincipal(long userId, UserRole role) {

    public static UserPrincipal of(long userId, UserRole role) {
        return new UserPrincipal(userId, role);
    }
}
