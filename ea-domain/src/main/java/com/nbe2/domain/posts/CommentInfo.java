package com.nbe2.domain.posts;

public record CommentInfo(String content) {
    public static CommentInfo of(final String content) {
        return new CommentInfo(content);
    }
}
