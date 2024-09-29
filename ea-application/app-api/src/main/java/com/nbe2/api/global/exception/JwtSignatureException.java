package com.nbe2.api.global.exception;

import java.security.SignatureException;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

public class JwtSignatureException extends WebException {
    public static final SignatureException EXCEPTION = new SignatureException();

    public JwtSignatureException(BaseErrorCode errorCode) {
        super(GlobalErrorCode.TOKEN_BAD_SIGNATURE);
    }
}
