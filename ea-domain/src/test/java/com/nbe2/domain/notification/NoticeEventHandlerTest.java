package com.nbe2.domain.notification;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.bookmark.BookmarkReader;
import com.nbe2.domain.notice.NoticeFixture;

@ExtendWith(MockitoExtension.class)
class NoticeEventHandlerTest {

    @InjectMocks private NoticeEventHandler noticeEventHandler;

    @Mock private BookmarkReader bookmarkReader;

    @Mock private EventPublisher eventPublisher;

    @Test
    @DisplayName("병원을 즐겨찾기한 사용자 수만큼 알림 이벤트를 발행한다.")
    void givenNoticeEvent_whenBookmarkedUserExist_thenShouldPublishMultipleEvents() {
        // given
        NoticeEvent event = NoticeEvent.of(NoticeFixture.create());
        List<Long> targetIds = List.of(1L, 2L);

        // when
        when(bookmarkReader.readUserIdsByEmergencyRoomId(anyLong())).thenReturn(targetIds);
        noticeEventHandler.handle(event);

        // then
        verify(eventPublisher, times(targetIds.size())).publish(any(NotificationEvent.class));
    }

    @Test
    @DisplayName("즐겨찾기한 사용자가 없으면 알림 이벤트를 발행하지 않는다.")
    void givenNoticeEvent_whenBookmarkedUserNotExist_thenShouldNotPublishEvent() {
        // given
        NoticeEvent event = NoticeEvent.of(NoticeFixture.create());
        List<Long> targetIds = Collections.emptyList();

        // when
        when(bookmarkReader.readUserIdsByEmergencyRoomId(anyLong())).thenReturn(targetIds);
        noticeEventHandler.handle(event);

        // then
        verify(eventPublisher, never()).publish(any(NotificationEvent.class));
    }
}
