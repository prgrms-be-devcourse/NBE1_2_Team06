package com.nbe2.api.global.exception;

import com.nbe2.common.exception.WebException;

public class JwtNotFountException extends WebException {

    public static final WebException EXCEPTION = new JwtNotFountException();

    private JwtNotFountException() {
        super(JwtErrorCode.ACCESS_TOKEN_NOT_FOUNT);
    }
}
