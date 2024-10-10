package com.nbe2.api.user.dto;

import com.nbe2.domain.user.UpdatePassword;

public record UpdatePasswordRequest(String previous, String toChange) {

    public UpdatePassword toPassword() {
        return new UpdatePassword(previous, toChange);
    }
}
