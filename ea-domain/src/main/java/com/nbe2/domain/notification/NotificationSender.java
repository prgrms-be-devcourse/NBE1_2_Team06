package com.nbe2.domain.notification;

public interface NotificationSender {

    void send(NewNotificationEvent event);
}
