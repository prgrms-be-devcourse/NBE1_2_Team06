package com.nbe2.api.global.util;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import com.nbe2.domain.auth.AuthConstants;
import com.nbe2.domain.auth.Tokens;

public class TokenUtils {

    public static HttpHeaders createTokenHeaders(Tokens tokens) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie =
                createHttpOnlyCookie(
                        AuthConstants.REFRESH_TOKEN_COOKIE_NAME,
                        tokens.refreshToken(),
                        AuthConstants.REFRESH_TOKEN_TTL);
        headers.set(HttpHeaders.AUTHORIZATION, tokens.accessToken());
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());
        return headers;
    }

    private static ResponseCookie createHttpOnlyCookie(String name, String value, Duration maxAge) {
        return ResponseCookie.from(name, value).httpOnly(true).path("/").maxAge(maxAge).build();
    }
}
