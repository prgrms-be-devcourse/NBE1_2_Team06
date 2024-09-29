package com.nbe2.api.global.exception;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUnsupportedException extends WebException {

    public static final UnsupportedJwtException EXCEPTION =
            new UnsupportedJwtException("지원하지 않는 토큰입니다.");

    public JwtUnsupportedException(BaseErrorCode errorCode) {
        super(GlobalErrorCode.UNSUPPORTED_TOKEN);
    }
}
