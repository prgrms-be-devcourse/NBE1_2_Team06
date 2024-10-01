package com.nbe2.domain.posts.exception;

import com.nbe2.common.exception.DomainException;

public class CommentNotFoundException extends DomainException {
    public static final DomainException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
