package com.nbe2.common.exception;

import lombok.Getter;

@Getter
public class WebException extends CustomException {

    public WebException(BaseErrorCode errorCode) {
        super(errorCode, "웹 계층 예외");
    }
}
