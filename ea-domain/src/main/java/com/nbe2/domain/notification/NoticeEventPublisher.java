package com.nbe2.domain.notification;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.Notice;

@Component
@RequiredArgsConstructor
public class NoticeEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(Notice notice) {
        applicationEventPublisher.publishEvent(NoticeEvent.of(notice));
    }
}
