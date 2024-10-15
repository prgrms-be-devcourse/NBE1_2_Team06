package com.nbe2.infra.notification.publisher;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.NotificationType;
import com.nbe2.domain.posts.CommentEventPublisher;
import com.nbe2.domain.posts.NewCommentEvent;

@Component
@RequiredArgsConstructor
public class RedisPublisher implements CommentEventPublisher {

    private final RedisTemplate<String, Object> notificationTemplate;

    @Override
    public void publish(NewCommentEvent event) {
        ChannelTopic topic = new ChannelTopic(NotificationType.COMMENT.getChannelId());
        notificationTemplate.convertAndSend(topic.getTopic(), event);
    }
}
