package com.nbe2.security.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

public class NoPermissionException extends WebException {

    public static final NoPermissionException EXCEPTION = new NoPermissionException();

    private NoPermissionException() {
        super(GlobalErrorCode.PERMISSION_DENIED);
    }
}
