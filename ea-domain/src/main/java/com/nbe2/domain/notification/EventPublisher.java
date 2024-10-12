package com.nbe2.domain.notification;

public interface EventPublisher {

    void publish(NotificationEvent event);
}
