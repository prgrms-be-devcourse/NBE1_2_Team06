package com.nbe2.domain.auth;

import java.time.Duration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConstants {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    public static final Duration REFRESH_TOKEN_TTL = Duration.ofDays(14);
}
