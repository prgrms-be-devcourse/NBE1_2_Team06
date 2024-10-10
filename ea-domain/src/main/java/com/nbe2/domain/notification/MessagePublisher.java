package com.nbe2.domain.notification;

public interface MessagePublisher {

    void publish(NotificationMessage message);
}
