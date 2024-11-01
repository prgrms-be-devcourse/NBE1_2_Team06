package com.nbe2.domain.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
class NotificationAppenderTest {

    @InjectMocks private NotificationAppender appender;

    @Mock private UserReader userReader;

    @Mock private NotificationRepository notificationRepository;

    @Test
    @DisplayName("사용자에게 온 알림을 저장한다.")
    void givenNotification_thenShouldSaveNotification() {
        // given
        NewNotification event = NotificationFixture.createNewNotification();
        User user = UserFixture.createUserWithId();

        // when
        when(userReader.read(anyLong())).thenReturn(user);
        appender.append(event);

        // then
        verify(notificationRepository).save(any(Notification.class));
    }
}
