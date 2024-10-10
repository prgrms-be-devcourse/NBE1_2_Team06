package com.nbe2.domain.posts;

public record CommentWriteInfo(Long userId, CommentInfo commentInfo) {
    public static CommentWriteInfo create(final Long userId, final CommentInfo commentInfo) {
        return new CommentWriteInfo(userId, commentInfo);
    }
}
