package com.nbe2.infra.emergencyroom.exception;

import com.nbe2.common.exception.InfraException;
import com.nbe2.domain.emergencyroom.exception.EmergencyRoomErrorCode;

public class RegionNotFoundException extends InfraException {

    public static final RegionNotFoundException EXCEPTION = new RegionNotFoundException();

    private RegionNotFoundException() {
        super(EmergencyRoomErrorCode.REGION_NOT_FOUND_ERROR);
    }
}
