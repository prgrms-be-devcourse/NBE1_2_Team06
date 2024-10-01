package com.nbe2.domain.posts.exception;

import com.nbe2.common.exception.DomainException;

public class CommentWriterMismatchException extends DomainException {
    public static final DomainException EXCEPTION = new CommentWriterMismatchException();

    private CommentWriterMismatchException() {
        super(CommentErrorCode.COMMENT_WRITER_MISMATCH);
    }
}
