package com.nbe2.domain.posts.service.dto;

import com.nbe2.domain.posts.entity.Post;

public record PostDetailsCommand(Long id, String name, String title, String content) {
    public static PostDetailsCommand from(Post post) {
        return new PostDetailsCommand(
                post.getId(), post.getUser().getName(), post.getTitle(), post.getContent());
    }
}
