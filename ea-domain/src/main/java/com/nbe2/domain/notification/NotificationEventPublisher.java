package com.nbe2.domain.notification;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.posts.Post;

@Component
@RequiredArgsConstructor
public class NotificationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publish(Post post) {
        eventPublisher.publishEvent(CommentEvent.of(post.getUser(), post.getTitle()));
    }
}
