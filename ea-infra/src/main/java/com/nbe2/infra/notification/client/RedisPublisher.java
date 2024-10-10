package com.nbe2.infra.notification.client;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.MessagePublisher;
import com.nbe2.domain.notification.NotificationMessage;

@Component
@RequiredArgsConstructor
public class RedisPublisher implements MessagePublisher {

    private final RedisTemplate<String, NotificationMessage> notificationTemplate;

    @Override
    public void publish(NotificationMessage message) {
        ChannelTopic topic = new ChannelTopic(message.channelId());
        notificationTemplate.convertAndSend(topic.getTopic(), message);
    }
}
