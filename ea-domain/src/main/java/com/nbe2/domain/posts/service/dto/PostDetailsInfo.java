package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.Post;

public record PostDetailsInfo(
        Long id, String name, String title, String content, Long commentCount) {
    public static PostDetailsInfo from(Post post) {

        return new PostDetailsInfo(
                post.getId(),
                post.getWriterName(),
                post.getTitle(),
                post.getContent(),
                post.getCommentCount());
    }
}
