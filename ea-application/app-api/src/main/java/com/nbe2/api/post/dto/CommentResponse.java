package com.nbe2.api.post.dto;

import com.nbe2.domain.posts.CommentReadInfo;

public record CommentResponse(CommentReadInfo commentReadInfo) {
    public static CommentResponse from(final CommentReadInfo commentReadInfo) {
        return new CommentResponse(commentReadInfo);
    }
}
