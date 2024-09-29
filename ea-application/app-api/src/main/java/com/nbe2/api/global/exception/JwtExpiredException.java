package com.nbe2.api.global.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

public class JwtExpiredException extends WebException {

    public static final JwtExpiredException EXCEPTION = new JwtExpiredException();

    private JwtExpiredException() {
        super(GlobalErrorCode.EXPIRED_TOKEN);
    }
}
