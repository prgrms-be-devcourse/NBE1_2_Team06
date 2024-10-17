package com.nbe2.infra.notification.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbe2.domain.notice.NewNoticeOfBookmarkedHospitalEvent;
import com.nbe2.domain.notification.NewNotification;
import com.nbe2.domain.notification.NotificationManager;
import com.nbe2.domain.notification.NotificationType;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoticeRedisSubscriber implements MessageListener {

    private final NotificationManager notificationManager;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            NewNoticeOfBookmarkedHospitalEvent event =
                    objectMapper.readValue(
                            message.getBody(), NewNoticeOfBookmarkedHospitalEvent.class);
            log.info("Notice event published: {}, to {}", event.referenceUri(), event.targetId());
            notificationManager.send(
                    NewNotification.of(
                            event.targetId(),
                            event.referenceUri(),
                            event.hospitalName(),
                            NotificationType.NOTICE));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
