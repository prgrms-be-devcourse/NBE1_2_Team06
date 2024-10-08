package com.nbe2.domain.notification;

import java.util.List;

public interface NotificationRepositoryCustom {

    List<Notification> findByUserIdWithCursor(long userId, long cursor, int size);

    Long findNextCursor(long userId, long lastCursor);
}
