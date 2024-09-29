package com.nbe2.api.global.exception;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

import io.jsonwebtoken.JwtException;

public class JwtNotValidateException extends WebException {

    public static final JwtException EXCEPTION = new JwtException("기간이 유효하지 않습니다.");

    public JwtNotValidateException(BaseErrorCode errorCode) {
        super(GlobalErrorCode.TOKEN_NOT_VALIDATE);
    }
}
