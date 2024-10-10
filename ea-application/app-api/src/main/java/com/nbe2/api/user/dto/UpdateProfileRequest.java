package com.nbe2.api.user.dto;

import com.nbe2.domain.user.UpdateProfile;

public record UpdateProfileRequest(String name, String email) {

    public UpdateProfile toProfile() {
        return new UpdateProfile(name, email);
    }
}
