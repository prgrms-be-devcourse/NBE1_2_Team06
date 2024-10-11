package com.nbe2.domain.posts;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.MessagePublisher;
import com.nbe2.domain.notification.NotificationMessage;
import com.nbe2.domain.user.User;

@Component
@RequiredArgsConstructor
public class CommentAppender {

    private final MessagePublisher messagePublisher;
    private final CommentRepository commentRepository;

    public Long append(final Post post, final User user, final CommentInfo info) {
        Comment comment = Comment.create(post, user, info.content());
        Comment saveComment = commentRepository.save(comment);
        messagePublisher.publish(NotificationMessage.from(post));
        return saveComment.getId();
    }
}
