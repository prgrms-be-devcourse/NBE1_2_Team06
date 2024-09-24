package com.nbe2.infra.feign.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.InfraException;

public class OtherServerExpiredTokenException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
