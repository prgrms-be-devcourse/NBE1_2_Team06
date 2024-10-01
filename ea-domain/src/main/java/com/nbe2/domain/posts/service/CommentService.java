package com.nbe2.domain.posts.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.component.CommentAppender;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserReader userReader;
    private final PostReader postReader;
    private final CommentAppender commentAppender;

    public Long save(final Long postId, final Long userId, final String content) {
        Post post = postReader.read(postId);
        User user = userReader.read(userId);
        commentAppender.append(Comment.builder().post(post).user(user).content(content).build());
        return postId;
    }
}
