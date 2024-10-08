package com.nbe2.domain.posts;

public record CommentReadInfo(String name, CommentInfo info) {

    public static CommentReadInfo from(final Comment comment) {
        return new CommentReadInfo(comment.getWriterName(), CommentInfo.of(comment.getContent()));
    }
}
