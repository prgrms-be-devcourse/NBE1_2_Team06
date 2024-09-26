package com.nbe2.api.auth.dto;

import com.nbe2.domain.user.UserProfile;

public record SignupRequest(String name, String email, String password) {

    public UserProfile toUserProfile() {
        return new UserProfile(name, email, password);
    }
}
