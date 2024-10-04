package com.nbe2.security.exception;

import com.nbe2.common.exception.WebException;

public class JwtExpriedException extends WebException {

    public static final WebException EXCEPTION = new JwtExpriedException();

    private JwtExpriedException() {
        super(JwtErrorCode.TOKEN_EXPIRED);
    }
}
