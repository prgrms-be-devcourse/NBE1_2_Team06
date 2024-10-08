package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import com.nbe2.domain.posts.exception.CommentWriterMismatchException;

@Component
public class CommentValidator {

    public void isOwnerId(final Long userId, final Comment comment) {
        Long commentWriterId = comment.getWriterId();
        if (!userId.equals(commentWriterId)) throw CommentWriterMismatchException.EXCEPTION;
    }
}
