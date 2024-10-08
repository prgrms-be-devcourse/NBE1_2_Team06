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
        return queryFactory
                .selectFrom(notification)
                .where(notification.owner.id.eq(userId))
                .where(notification.id.loe(cursor))
                .orderBy(notification.id.desc())
                .limit(size)
                .fetch();
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
}
