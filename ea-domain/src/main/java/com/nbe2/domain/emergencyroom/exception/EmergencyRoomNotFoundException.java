package com.nbe2.domain.emergencyroom.exception;

import com.nbe2.common.exception.DomainException;

public class EmergencyRoomNotFoundException extends DomainException {

    public static final EmergencyRoomNotFoundException EXCEPTION =
            new EmergencyRoomNotFoundException();

    private EmergencyRoomNotFoundException() {
        super(EmergencyRoomErrorCode.EMERGENCY_ROOM_NOT_FOUND_ERROR);
    }
}
