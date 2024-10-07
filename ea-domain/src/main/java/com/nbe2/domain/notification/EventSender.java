package com.nbe2.domain.notification;

public interface EventSender {
    void send(CommentEvent event);
}
