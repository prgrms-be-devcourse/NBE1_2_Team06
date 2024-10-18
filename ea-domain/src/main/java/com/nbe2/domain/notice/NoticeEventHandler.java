package com.nbe2.domain.notice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.bookmark.BookmarkReader;

@Component
@RequiredArgsConstructor
public class NoticeEventHandler {

    private final BookmarkReader bookmarkReader;
    private final NoticeEventPublisher eventPublisher;

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(NewNoticeEvent event) {
        bookmarkReader
                .readUserIdsByEmergencyRoomId(event.notice().getEmergencyRoom().getId())
                .forEach(
                        id ->
                                eventPublisher.publish(
                                        NewNoticeOfBookmarkedHospitalEvent.of(id, event.notice())));
    }
}
