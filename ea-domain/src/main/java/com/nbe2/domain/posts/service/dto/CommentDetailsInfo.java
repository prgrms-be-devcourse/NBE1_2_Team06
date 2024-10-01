package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.Comment;

public record CommentDetailsInfo(String name, String content) {

    public static CommentDetailsInfo from(final Comment comment) {
        return new CommentDetailsInfo(comment.getUserName(), comment.getContent());
    }
}
