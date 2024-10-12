package com.nbe2.infra.notification.client;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.EventPublisher;
import com.nbe2.domain.notification.NotificationEvent;

@Component
@RequiredArgsConstructor
public class RedisPublisher implements EventPublisher {

    private final RedisTemplate<String, NotificationEvent> notificationTemplate;

    @Override
    public void publish(NotificationEvent event) {
        ChannelTopic topic = new ChannelTopic("notification");
        notificationTemplate.convertAndSend(topic.getTopic(), event);
    }
}
