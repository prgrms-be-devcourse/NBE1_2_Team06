package com.nbe2.security.exception;

import io.jsonwebtoken.JwtException;

public class JwtNotValidateException extends JwtException {

    public static final JwtException EXCEPTION = new JwtException("기간이 유효하지 않습니다.");

    private JwtNotValidateException() {
        super(String.valueOf(JwtErrorCode.TOKEN_NOT_VALIDATE));
    }
}
