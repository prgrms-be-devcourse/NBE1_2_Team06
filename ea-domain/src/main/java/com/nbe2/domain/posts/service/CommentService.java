package com.nbe2.domain.posts.service;

import java.util.List;

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
import com.nbe2.domain.posts.service.dto.CommentDetailsInfo;
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

    @Transactional(readOnly = true)
    public List<CommentDetailsInfo> findByUserId(final Long userId) {
        User user = userReader.read(userId);
        List<Comment> comments = commentReader.read(user);
        return comments.stream().map(CommentDetailsInfo::from).toList();
    }

    @Transactional(readOnly = true)
    public List<CommentDetailsInfo> findByUserEmail(final String email) {
        User user = userReader.read(email);
        List<Comment> comments = commentReader.read(user);
        return comments.stream().map(CommentDetailsInfo::from).toList();
    }

    public Long update(final Long commentsId, final CommentDefaultInfo info) {
        Comment comment = commentReader.read(commentsId);
        commentUpdater.update(comment, info);
        return comment.getPostId();
    }
}
