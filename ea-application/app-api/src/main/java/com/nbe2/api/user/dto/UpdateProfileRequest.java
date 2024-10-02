package com.nbe2.api.user.dto;

import com.nbe2.domain.user.UserProfile;

public record UpdateProfileRequest(String name, String email, String password) {

    public UserProfile toProfile() {
        return new UserProfile(name, email, password);
    }
}
