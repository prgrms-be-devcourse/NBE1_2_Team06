package com.nbe2.domain.bookmark;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

@ExtendWith(MockitoExtension.class)
class BookmarkDeleterTest {

    @InjectMocks private BookmarkDeleter bookmarkDeleter;

    @Mock private BookmarkRepository bookmarkRepository;

    private EmergencyRoom expectedEmergencyRoom;
    private User expectedUser;

    @BeforeEach
    void setup() {
        expectedEmergencyRoom = EmergencyRoomFixture.create();
        expectedUser = UserFixture.createUserWithId();
    }

    @Test
    @DisplayName("즐겨찾기 객체를 통해 즐겨찾기를 삭제한다.")
    void givenBookmark_whenDeleteBookmark_thenShouldDeleteBookmark() {
        // given
        Bookmark bookmark = Bookmark.from(expectedUser, expectedEmergencyRoom);

        // when
        bookmarkDeleter.deleteBookmark(bookmark);

        // then
        verify(bookmarkRepository, times(1)).delete(bookmark);
    }
}
