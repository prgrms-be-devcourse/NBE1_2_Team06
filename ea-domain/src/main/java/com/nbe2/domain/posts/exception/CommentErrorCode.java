package com.nbe2.domain.posts.exception;

import static com.nbe2.common.constants.EAConstants.CONFLICT;
import static com.nbe2.common.constants.EAConstants.NOT_FOUND;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.exception.BaseErrorCode;
import com.nbe2.common.exception.ErrorReason;

@RequiredArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {
    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404_1", "존재하지 않는 댓글입니다."),
    COMMENT_REGISTER_CONFLICT(CONFLICT, "COMMENT_409_1", "댓글 등록 중 충돌이 발생했습니다."),
    COMMENT_DELETE_CONFLICT(CONFLICT, "COMMENT_409_2", "댓글 삭제 중 충돌이 발생했습니다.");

    private final Integer status;
    private final String errorCode;
    private final String message;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, errorCode, message);
    }
}
