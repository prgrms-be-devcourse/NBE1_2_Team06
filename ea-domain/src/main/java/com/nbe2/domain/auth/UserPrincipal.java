package com.nbe2.domain.auth;

import com.nbe2.domain.user.UserRole;

public record UserPrincipal(long userId, UserRole role) {}
