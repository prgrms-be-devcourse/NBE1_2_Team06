package com.nbe2.domain.posts;

public record PostListInfo(
        Long id, String name, String title, String content, Long likeCount, Long commentCount) {}
