package com.nbe2.security.exception;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUnsupportedException extends JwtException {

    public static final JwtException EXCEPTION = new UnsupportedJwtException("지원하지 않는 토큰입니다.");

    private JwtUnsupportedException() {
        super(String.valueOf(JwtErrorCode.UNSUPPORTED_TOKEN));
    }
}
