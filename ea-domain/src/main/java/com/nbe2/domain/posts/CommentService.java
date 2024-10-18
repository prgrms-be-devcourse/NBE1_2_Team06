package com.nbe2.domain.posts;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final UserReader userReader;
    private final PostReader postReader;
    private final CommentAppender commentAppender;
    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;
    private final CommentValidator commentValidator;
    private final CommentEventPublisher eventPublisher;

    public Long save(final Long postId, final CommentWriteInfo writeInfo) {
        Post post = postReader.readWithPessimisticWriteLock(postId);
        User user = userReader.read(writeInfo.userId());
        commentAppender.append(post, user, writeInfo.commentInfo());
        post.increaseCommentCount();
        eventPublisher.publish(NewCommentEvent.from(post));
        return postId;
    }

    @Transactional(readOnly = true)
    public List<CommentReadInfo> getPostComments(final Long postId) {
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

    public void delete(final Long commentsId) {
        Comment comment = commentReader.read(commentsId);
        Post post = postReader.readWithPessimisticWriteLock(comment.getPostId());
        commentDeleter.delete(comment);
        post.decreaseCommentCount();
    }
}
