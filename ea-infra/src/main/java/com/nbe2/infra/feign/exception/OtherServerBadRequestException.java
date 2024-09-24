package com.nbe2.infra.feign.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.InfraException;

public class OtherServerBadRequestException extends InfraException {

    public static final InfraException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
