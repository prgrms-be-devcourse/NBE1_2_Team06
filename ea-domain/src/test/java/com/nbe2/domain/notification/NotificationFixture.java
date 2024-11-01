package com.nbe2.domain.notification;

import static com.nbe2.domain.global.TestConstants.ID;

import java.lang.reflect.Field;

import com.nbe2.domain.user.UserFixture;

public class NotificationFixture {

    public static final String REFERENCE_URI = "reference uri";
    public static final String TITLE = "notification";

    public static Notification createCommentNotification() {
        return Notification.builder()
                .target(UserFixture.createUserWithId())
                .referenceUri(REFERENCE_URI)
                .type(NotificationType.COMMENT)
                .title(TITLE)
                .build();
    }

    public static Notification createCommentNotificationWithId(long id) {
        Notification notification = createCommentNotification();

        try {
            Field field = notification.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(notification, id);
        } catch (Exception ignored) {
        }

        return notification;
    }

    public static NewNotification createNewNotification() {
        return NewNotification.of(ID, REFERENCE_URI, TITLE, NotificationType.COMMENT);
    }
}
