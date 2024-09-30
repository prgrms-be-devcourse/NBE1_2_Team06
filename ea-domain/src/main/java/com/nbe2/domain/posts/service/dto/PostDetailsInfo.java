package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.Post;

public record PostDetailsInfo(Long id, String name, String title, String content) {
    public static PostDetailsInfo from(Post post) {
        return new PostDetailsInfo(
                post.getId(), post.getUser().getName(), post.getTitle(), post.getContent());
    }
}
