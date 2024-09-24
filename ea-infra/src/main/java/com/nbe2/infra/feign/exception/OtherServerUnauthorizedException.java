package com.nbe2.infra.feign.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.InfraException;

public class OtherServerUnauthorizedException extends InfraException {

    public static final OtherServerUnauthorizedException EXCEPTION =
            new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED);
    }
}
