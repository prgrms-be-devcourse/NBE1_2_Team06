package com.nbe2.domain.posts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.component.CommentAppender;
import com.nbe2.domain.posts.service.component.CommentReader;
import com.nbe2.domain.posts.service.component.CommentUpdater;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.dto.CommentDefaultInfo;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final UserReader userReader;
    private final PostReader postReader;
    private final CommentAppender commentAppender;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;

    public Long save(final Long postId, final Long userId, final CommentDefaultInfo info) {
        Post post = postReader.read(postId);
        User user = userReader.read(userId);
        commentAppender.append(
                Comment.builder().post(post).user(user).content(info.content()).build());
        return postId;
    }

    public Long update(final Long commentsId, final CommentDefaultInfo info) {
        Comment comment = commentReader.read(commentsId);
        commentUpdater.update(comment, info);
        return comment.getPostId();
    }
}
