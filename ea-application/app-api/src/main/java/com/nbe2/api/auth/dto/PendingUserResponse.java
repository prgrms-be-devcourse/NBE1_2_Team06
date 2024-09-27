package com.nbe2.api.auth.dto;

import com.nbe2.domain.user.UserProfileWithLicense;

public record PendingUserResponse(Long id, String name, String email, Long fileId) {

    public static PendingUserResponse from(UserProfileWithLicense user) {
        return new PendingUserResponse(user.id(), user.name(), user.email(), user.licenseId());
    }
}
