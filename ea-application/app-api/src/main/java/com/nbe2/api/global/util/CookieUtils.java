package com.nbe2.api.global.util;

import java.time.Duration;

import org.springframework.http.ResponseCookie;

public class CookieUtils {

    public static ResponseCookie createHttpOnlyCookie(String name, String value, Duration maxAge) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .path("/")
                .maxAge(maxAge)
                .sameSite("Strict")
                .build();
    }
}
