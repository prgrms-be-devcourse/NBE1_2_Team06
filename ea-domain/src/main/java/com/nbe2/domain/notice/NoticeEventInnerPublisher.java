package com.nbe2.domain.notice;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeEventInnerPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(Notice notice) {
        applicationEventPublisher.publishEvent(NewNoticeEvent.from(notice));
    }
}
