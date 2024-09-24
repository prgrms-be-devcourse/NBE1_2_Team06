package com.nbe2.infra.feign.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.InfraException;

public class OtherServerForbiddenException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(GlobalErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
