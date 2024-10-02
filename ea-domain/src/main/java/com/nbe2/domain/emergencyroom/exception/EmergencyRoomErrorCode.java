package com.nbe2.domain.emergencyroom.exception;

import static com.nbe2.common.constants.EAConstants.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum EmergencyRoomErrorCode implements BaseErrorCode {
    REGION_NOT_FOUND_ERROR(BAD_REQUEST, "EMR_400_1", "좌표에 해당하는 지역을 찾을 수 없습니다"),
    EMERGENCY_ROOM_NOT_FOUND_ERROR(BAD_REQUEST, "EMR_400_2", "해당 ID의 응급실 정보가 없습니다"),
    INVALID_COORDINATE(BAD_REQUEST, "EMR_400_4", "유효하지 않은 좌표입니다"),
    REAL_TIME_EMERGENCY_ROOM_INFO_NOT_FOUND_ERROR(
            BAD_REQUEST, "EMR_400_3", "해당 ID의 실시간 응급실 정보가 없습니다"),
    ;

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
