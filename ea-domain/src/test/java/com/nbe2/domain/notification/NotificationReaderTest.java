package com.nbe2.domain.notification;

import static com.nbe2.domain.global.TestConstants.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.common.dto.Cursor;

@ExtendWith(MockitoExtension.class)
class NotificationReaderTest {

    @InjectMocks private NotificationReader notificationReader;

    @Mock private NotificationRepository notificationRepository;

    @Test
    @DisplayName("알림 기록이 있을 때 상세 리스트를 반환한다.")
    void givenCursor_whenNotificationExists_thenShouldReturnNotifications() {
        // given
        Cursor cursor = new Cursor(Long.MAX_VALUE, 2);
        List<Notification> notifications =
                List.of(
                        NotificationFixture.createCommentNotificationWithId(2),
                        NotificationFixture.createCommentNotificationWithId(1));
        List<NotificationDetail> expected =
                notifications.stream().map(NotificationDetail::from).toList();

        // when
        when(notificationRepository.findByUserIdWithCursor(anyLong(), anyLong(), anyInt()))
                .thenReturn(notifications);
        List<NotificationDetail> actual = notificationReader.read(ID, cursor);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("알림 기록이 없을 때 빈 리스트를 반환한다.")
    void givenCursor_whenNotificationNotExists_thenShouldReturnEmptyList() {
        // given
        Cursor cursor = new Cursor(Long.MAX_VALUE, 2);
        List<Notification> notifications = Collections.emptyList();
        List<NotificationDetail> expected = Collections.emptyList();

        // when
        when(notificationRepository.findByUserIdWithCursor(anyLong(), anyLong(), anyInt()))
                .thenReturn(notifications);
        List<NotificationDetail> actual = notificationReader.read(ID, cursor);

        // then
        assertEquals(expected, actual);
    }
}
