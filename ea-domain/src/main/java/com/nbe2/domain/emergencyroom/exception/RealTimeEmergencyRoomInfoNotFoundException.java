package com.nbe2.domain.emergencyroom.exception;

import com.nbe2.common.exception.InfraException;

public class RealTimeEmergencyRoomInfoNotFoundException extends InfraException {

    public static final RealTimeEmergencyRoomInfoNotFoundException EXCEPTION =
            new RealTimeEmergencyRoomInfoNotFoundException();

    private RealTimeEmergencyRoomInfoNotFoundException() {
        super(EmergencyRoomErrorCode.REAL_TIME_EMERGENCY_ROOM_INFO_NOT_FOUND_ERROR);
    }
}
