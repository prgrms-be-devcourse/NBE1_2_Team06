package com.nbe2.domain.posts.service.dto;

public record CommentDefaultInfo(String content) {
    public static CommentDefaultInfo create(String content) {
        return new CommentDefaultInfo(content);
    }
}
