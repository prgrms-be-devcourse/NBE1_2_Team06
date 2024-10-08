package com.nbe2.domain.posts;

public record LikeInfo(Long postId, Long userId) {
    public static LikeInfo of(final Long postId, final Long userId) {
        return new LikeInfo(postId, userId);
    }
}
