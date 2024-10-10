package com.nbe2.api.notification.sse.exception;

import com.nbe2.common.exception.WebException;

public class SseConnectionException extends WebException {

    public static final WebException EXCEPTION = new SseConnectionException();

    private SseConnectionException() {
        super(SseErrorCode.SSE_CONNECTION_FAILED);
    }
}
