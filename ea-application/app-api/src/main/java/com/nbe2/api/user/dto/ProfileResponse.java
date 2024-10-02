package com.nbe2.api.user.dto;

import com.nbe2.domain.user.MyProfile;

public record ProfileResponse(String name, String email, boolean hasMedicalAuthority) {

    public static ProfileResponse from(MyProfile profile) {
        return new ProfileResponse(profile.name(), profile.email(), profile.hasMedicalAuthority());
    }
}
