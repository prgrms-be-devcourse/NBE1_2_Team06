package com.nbe2.security.exception;

import com.nbe2.common.exception.WebException;

public class JwtUnkownException extends WebException {

    public static final WebException EXCEPTION = new JwtUnkownException();

    private JwtUnkownException() {
        super(JwtErrorCode.UNKNOWN_EXCEPTION);
    }
}
