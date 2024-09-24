package com.nbe2.common.exception;

import lombok.Getter;

@Getter
public class DomainException extends CustomException {

    public DomainException(BaseErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DomainException(BaseErrorCode errorCode) {
        super(errorCode, "도메인 계층 예외");
    }
}
