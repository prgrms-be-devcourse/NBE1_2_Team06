package com.nbe2.domain.notification;

import static com.nbe2.domain.global.TestConstants.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.common.dto.Cursor;
import com.nbe2.common.dto.CursorResult;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks private NotificationService notificationService;

    @Mock private NotificationUpdater notificationUpdater;

    @Mock private NotificationReader notificationReader;

    @Test
    @DisplayName("사용자의 알림 기록의 마지막 페이지를 조회하고 읽음 처리한다.")
    void givenUserIdAndCursor_whenReadLastPage_thenShouldNotReturnNextCursorAndReadNotifications() {
        // given
        long userId = ID;
        Cursor cursor = new Cursor(Long.MAX_VALUE, 2);
        List<NotificationDetail> details =
                Stream.of(
                                NotificationFixture.createCommentNotificationWithId(2),
                                NotificationFixture.createCommentNotificationWithId(1))
                        .map(NotificationDetail::from)
                        .toList();
        CursorResult<NotificationDetail> expected = new CursorResult<>(details, null);

        // when
        when(notificationReader.read(anyLong(), any(Cursor.class))).thenReturn(details);
        when(notificationReader.getNextCursor(anyLong(), anyLong())).thenReturn(null);

        // then
        CursorResult<NotificationDetail> actual =
                notificationService.getNotificationHistory(userId, cursor);
        verify(notificationUpdater).readAllUnreadNotifications(userId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("알림의 중간 페이지 조회 시 다음 커서를 함께 반환하고 알림들을 읽음 처리한다.")
    void givenUserIdAndCursor_whenHasNextPage_thenShouldReturnNextCursorAndReadNotifications() {
        // given
        long userId = ID;
        Cursor cursor = new Cursor(Long.MAX_VALUE, 2);
        List<NotificationDetail> details =
                Stream.of(
                                NotificationFixture.createCommentNotificationWithId(5),
                                NotificationFixture.createCommentNotificationWithId(4))
                        .map(NotificationDetail::from)
                        .toList();
        CursorResult<NotificationDetail> expected = new CursorResult<>(details, 3L);

        // when
        when(notificationReader.read(anyLong(), any(Cursor.class))).thenReturn(details);
        when(notificationReader.getNextCursor(anyLong(), anyLong())).thenReturn(3L);

        // then
        CursorResult<NotificationDetail> actual =
                notificationService.getNotificationHistory(userId, cursor);
        verify(notificationUpdater).readAllUnreadNotifications(userId);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("알림 기록이 없을 때 빈 리스트와 커서를 반환한다.")
    void
            givenUserIdAndCursor_whenHistoryNotExists_thenShouldReturnEmptyResultAndReadNotifications() {
        // given
        long userId = ID;
        Cursor cursor = new Cursor(Long.MAX_VALUE, 2);
        CursorResult<NotificationDetail> expected =
                new CursorResult<>(Collections.emptyList(), null);

        // when
        when(notificationReader.read(anyLong(), any(Cursor.class)))
                .thenReturn(Collections.emptyList());

        // then
        CursorResult<NotificationDetail> actual =
                notificationService.getNotificationHistory(userId, cursor);
        verify(notificationUpdater).readAllUnreadNotifications(userId);
        assertEquals(expected, actual);
    }
}
