package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.NotificationEventPublisher;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class CommentAppender {

    private final NotificationEventPublisher notificationEventPublisher;
    private final CommentRepository commentRepository;

    public Long append(final Post post, final User user, final CommentInfo info) {
        Comment comment = Comment.create(post, user, info.content());
        Comment saveComment = commentRepository.save(comment);
        notificationEventPublisher.publish(post);
        return saveComment.getId();
    }
}
