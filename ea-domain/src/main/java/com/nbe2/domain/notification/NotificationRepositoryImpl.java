package com.nbe2.domain.notification;

import static com.nbe2.domain.notification.QNotification.notification;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Notification> findByUserIdWithCursor(long userId, long cursor, int size) {
        List<Notification> content =
                queryFactory
                        .selectFrom(notification)
                        .where(notification.owner.id.eq(userId))
                        .where(notification.id.loe(cursor))
                        .orderBy(notification.id.desc())
                        .limit(size)
                        .fetch();
        return content;
    }

    @Override
    public Long findNextCursor(long userId, long lastCursor) {
        return queryFactory
                .select(notification.id)
                .from(notification)
                .where(notification.owner.id.eq(userId))
                .where(notification.id.lt(lastCursor))
                .orderBy(notification.id.desc())
                .fetchFirst();
    }

    @Override
    public void setIsRead(long userId, boolean isRead) {
        queryFactory
                .update(notification)
                .set(notification.isRead, isRead)
                .where(notification.owner.id.eq(userId))
                .where(notification.isRead.eq(!isRead))
                .execute();
    }
}
