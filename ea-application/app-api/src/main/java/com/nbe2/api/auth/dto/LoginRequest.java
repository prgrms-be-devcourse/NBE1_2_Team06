package com.nbe2.api.auth.dto;

import com.nbe2.domain.auth.Login;

public record LoginRequest(String email, String password) {

    public Login toLogin() {
        return new Login(email, password);
    }
}
