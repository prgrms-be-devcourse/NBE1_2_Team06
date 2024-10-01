package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.exception.CommentWriterMismatchException;

@Component
public class CommentValidator {

    public void isOwnerId(final Long userId, final Comment comment) {
        Long commentWriterId = comment.getUserId();
        if (!userId.equals(commentWriterId)) throw CommentWriterMismatchException.EXCEPTION;
    }
}
