package com.nbe2.domain.posts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.entity.Comment;
import com.nbe2.domain.posts.entity.Post;
import com.nbe2.domain.posts.service.component.*;
import com.nbe2.domain.posts.service.dto.CommentReadInfo;
import com.nbe2.domain.posts.service.dto.CommentWriteInfo;
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
    private final CommentDeleter commentDeleter;
    private final CommentValidator commentValidator;

    public Long save(final Long postId, final CommentWriteInfo writeInfo) {
        Post post = postReader.readWithPessimisticWriteLock(postId);
        User user = userReader.read(writeInfo.userId());
        commentAppender.append(post, user, writeInfo.commentInfo());
        post.increaseCommentCount();
        return postId;
    }

    @Transactional(readOnly = true)
    public List<CommentReadInfo> findPostComments(final Long postId) {
        Post post = postReader.read(postId);
        List<Comment> comments = commentReader.read(post);
        return comments.stream().map(CommentReadInfo::from).toList();
    }

    public Long update(final Long commentsId, final CommentWriteInfo writeInfo) {
        Comment comment = commentReader.read(commentsId);
        commentValidator.isOwnerId(writeInfo.userId(), comment);
        commentUpdater.update(comment, writeInfo.commentInfo());
        return comment.getPostId();
    }

    public Long delete(final Long commentsId) {
        Comment comment = commentReader.read(commentsId);
        Post post = postReader.readWithPessimisticWriteLock(comment.getPostId());
        commentDeleter.delete(comment);
        post.decreaseCommentCount();
        return comment.getPostId();
    }
}
