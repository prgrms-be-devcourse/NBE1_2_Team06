package com.nbe2.infra.notification.publisher;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.NewNoticeOfBookmarkedHospitalEvent;
import com.nbe2.domain.notice.NoticeEventPublisher;
import com.nbe2.domain.notification.NotificationType;

@Component
@RequiredArgsConstructor
public class NoticeRedisPublisher implements NoticeEventPublisher {

    private final RedisTemplate<String, Object> notificationTemplate;

    @Override
    public void publish(NewNoticeOfBookmarkedHospitalEvent event) {
        ChannelTopic topic = new ChannelTopic(NotificationType.NOTICE.getChannelId());
        notificationTemplate.convertAndSend(topic.getTopic(), event);
    }
}
