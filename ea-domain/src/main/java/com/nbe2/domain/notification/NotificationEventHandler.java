package com.nbe2.domain.notification;

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
    private final NotificationService notificationService;

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCommentEvent(CommentEvent event) {
        notificationService.sendNotification(event);
        eventSender.send(event);
    }
}
