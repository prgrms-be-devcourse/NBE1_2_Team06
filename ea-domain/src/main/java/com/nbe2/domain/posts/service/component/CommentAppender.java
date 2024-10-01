package com.nbe2.domain.posts.service.component;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.repository.CommentRepository;
import com.nbe2.domain.posts.service.dto.CommentInfo;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class CommentAppender {
    private final CommentRepository commentRepository;

    public Long append(final Post post, final User user, final CommentInfo info) {
        Comment comment = Comment.builder().post(post).user(user).content(info.content()).build();
        Comment saveComment = commentRepository.save(comment);
        return saveComment.getId();
    }
}
