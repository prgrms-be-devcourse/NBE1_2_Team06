package com.nbe2.domain.emergencyroom.exception;

import com.nbe2.common.exception.DomainException;

public class InvalidCoordinateException extends DomainException {

    public static final InvalidCoordinateException EXCEPTION = new InvalidCoordinateException();

    private InvalidCoordinateException() {
        super(EmergencyRoomErrorCode.INVALID_COORDINATE);
    }
}
