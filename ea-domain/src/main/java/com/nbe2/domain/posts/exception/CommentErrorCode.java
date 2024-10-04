package com.nbe2.domain.posts.exception;

import static com.nbe2.common.constants.EAConstants.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {
    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404_1", "존재하지 않는 댓글입니다."),
    COMMENT_WRITER_MISMATCH(BAD_REQUEST, "COMMENT_400_1", "댓글 작성자가 일치하지 않습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
