package com.nbe2.domain.posts.exception;

import com.nbe2.common.exception.DomainException;

public class CommentDeleteConflictException extends DomainException {
    public static final DomainException EXCEPTION = new CommentDeleteConflictException();

    private CommentDeleteConflictException() {
        super(CommentErrorCode.COMMENT_DELETE_CONFLICT);
    }
}
