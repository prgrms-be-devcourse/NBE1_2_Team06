package com.nbe2.domain.file.exception;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum FileMetaDataErrorCode implements BaseErrorCode {
    FILE_META_DATA_NOT_FOUND_ERROR(400, "FMD_400_1", "해당 ID의 파일 메타데이터가 없습니다"),
    ;

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
