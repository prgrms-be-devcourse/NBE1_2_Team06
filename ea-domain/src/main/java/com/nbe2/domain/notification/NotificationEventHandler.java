package com.nbe2.domain.notification;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final EventSender eventSender;
    private final NotificationAppender notificationAppender;
    private final NotificationUpdater notificationUpdater;

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCommentEvent(CommentEvent event) {
        notificationAppender.append(event);
        eventSender.send(event);
    }

    @Async
    @EventListener
    @Transactional
    public void handleReadEvent(ReadEvent event) {
        notificationUpdater.readAllUnreadNotifications(event.getOwnerId());
    }
}
