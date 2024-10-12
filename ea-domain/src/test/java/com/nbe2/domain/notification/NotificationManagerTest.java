package com.nbe2.domain.notification;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class NotificationManagerTest {

    @InjectMocks private NotificationManager notificationManager;

    @Mock private NotificationAppender notificationAppender;

    @Mock private EventSender eventSender;

    @Test
    @DisplayName("알림 이벤트 시 알림을 저장하고 이벤트를 대상 회원에게 전송한다.")
    void
            givenNotificationEvent_whenEventIsValid_thenShouldAppendNotificationAndSendEventToTarget() {
        // given
        NotificationEvent event = NotificationFixture.createCommentNotificationEvent();

        // when
        notificationManager.send(event);

        // then
        verify(notificationAppender).append(event);
        verify(eventSender).send(event);
    }

    @Test
    @DisplayName("알림 저장 중 예외가 발생하면 이벤트는 전송하지 않고 예외가 전파된다.")
    void givenNotificationEvent_whenEventIsInvalid_thenShouldNotSendEventAndThrowException() {
        // given
        NotificationEvent event = NotificationFixture.createCommentNotificationEvent();

        // when
        doThrow(UserNotFoundException.class).when(notificationAppender).append(event);

        // then
        verify(eventSender, never()).send(event);
        assertThrows(UserNotFoundException.class, () -> notificationManager.send(event));
    }
}
