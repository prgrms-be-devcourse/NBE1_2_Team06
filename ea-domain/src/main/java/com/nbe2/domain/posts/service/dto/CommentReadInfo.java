package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.Comment;

public record CommentReadInfo(String name, CommentInfo info) {

    public static CommentReadInfo from(final Comment comment) {
        return new CommentReadInfo(comment.getWriterName(), CommentInfo.of(comment.getContent()));
    }
}
