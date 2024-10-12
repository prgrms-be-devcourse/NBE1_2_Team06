package com.nbe2.infra.notification.client;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbe2.domain.notification.NotificationEvent;
import com.nbe2.domain.notification.NotificationManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {

    private final NotificationManager notificationManager;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            NotificationEvent event =
                    objectMapper.readValue(message.getBody(), NotificationEvent.class);
            log.info(
                    "Notification event published: {}, to {}",
                    event.referenceUri(),
                    event.targetId());
            notificationManager.send(event);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
