package com.nbe2.domain.posts.service.dto;

import java.util.List;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;

public record PostDetailsInfo(
        Long id,
        String name,
        String title,
        String content,
        Long commentCount,
        List<CommentDetailsInfo> commentInfos) {
    public static PostDetailsInfo from(Post post, List<Comment> comments) {

        return new PostDetailsInfo(
                post.getId(),
                post.getUser().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCommentCount(),
                comments.stream().map(CommentDetailsInfo::from).toList());
    }
}
