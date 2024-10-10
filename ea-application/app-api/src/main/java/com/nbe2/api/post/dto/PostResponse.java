package com.nbe2.api.post.dto;

import com.nbe2.domain.posts.PostDetailsInfo;

public record PostResponse(PostDetailsInfo postDetailsInfo) {
    public static PostResponse from(PostDetailsInfo postDetailsInfo) {
        return new PostResponse(postDetailsInfo);
    }
}
