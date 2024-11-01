package com.nbe2.domain.bookmark;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

class BookmarkServiceTest {
    @InjectMocks private BookmarkService bookmarkService;

    @Mock private BookmarkAppender bookmarkAppender;

    @Mock private BookmarkDeleter bookmarkDeleter;

    @Mock private BookmarkReader bookmarkReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("사용자가 즐겨찾기를 추가한다.")
    void givenUserIdEmergencyRoomId_whenBookmarkAppenderSave_thenShouldBookmarkSave() {
        // given
        long userId = 1L;
        long emergencyRoomId = 1L;

        // when
        bookmarkService.saveBookmark(userId, emergencyRoomId);

        // then
        verify(bookmarkAppender, times(1)).save(eq(emergencyRoomId), eq(userId));
    }

    @Test
    @DisplayName("사용자가 즐겨찾기를 조회한다.")
    void givenUserId_whenBookmarkReaderReadByUserId_thenShouldBookmarkReadInfoFrom() {
        // given
        User user = UserFixture.createUserWithId();
        EmergencyRoom emergencyRoom1 = EmergencyRoomFixture.create();
        EmergencyRoom emergencyRoom2 = EmergencyRoomFixture.create();
        List<Bookmark> bookmarks =
                List.of(Bookmark.from(user, emergencyRoom1), Bookmark.from(user, emergencyRoom2));
        long userId = user.getId();
        // when
        when(bookmarkReader.readByUserId(userId)).thenReturn(bookmarks);

        // then
        List<Bookmark> result = bookmarkReader.readByUserId(userId);
        verify(bookmarkReader, times(1)).readByUserId(userId);
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("사용자가 즐겨찾기를 삭제한다.")
    void
            givenUserIdEmnergencyRoomId_whenBookmarkReaderReadByEmergencyRoomIdAndUserId_thenBookmarkDelete() {
        // Given
        Long emergencyRoomId = 1L;
        Long userId = 2L;
        Bookmark bookmark = mock(Bookmark.class);
        when(bookmarkReader.readByEmergencyRoomIdAndUserId(emergencyRoomId, userId))
                .thenReturn(bookmark);

        // When
        bookmarkService.deleteBookmark(emergencyRoomId, userId);

        // Then
        verify(bookmarkReader, times(1)).readByEmergencyRoomIdAndUserId(emergencyRoomId, userId);
        verify(bookmarkDeleter, times(1)).deleteBookmark(bookmark);
    }
}
