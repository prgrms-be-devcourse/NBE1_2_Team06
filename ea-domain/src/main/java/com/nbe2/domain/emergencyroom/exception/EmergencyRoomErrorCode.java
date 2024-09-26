package com.nbe2.domain.emergencyroom.exception;

import static com.nbe2.common.constants.EAConstants.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum EmergencyRoomErrorCode implements BaseErrorCode {
    REGION_NOT_FOUND_ERROR(BAD_REQUEST, "EMR_400_1", "좌표에 해당하는 지역을 찾을 수 없습니다"),
    ;

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
