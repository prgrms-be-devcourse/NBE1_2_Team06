package com.nbe2.domain.posts.exception;

import com.nbe2.common.exception.DomainException;

public class CommentRegisterConflictException extends DomainException {
    public static final DomainException EXCEPTION = new CommentRegisterConflictException();

    private CommentRegisterConflictException() {
        super(CommentErrorCode.COMMENT_REGISTER_CONFLICT);
    }
}
