package com.nbe2.api.auth.dto;

public record OAuthConnectionResponse(String url) {

    public static OAuthConnectionResponse from(String url) {
        return new OAuthConnectionResponse(url);
    }
}
