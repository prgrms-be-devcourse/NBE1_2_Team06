package com.nbe2.domain.bookmark;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;

@ExtendWith(MockitoExtension.class)
public class BookmarkAppenderTest {

    @InjectMocks private BookmarkAppender bookmarkAppender;

    @Mock private BookmarkRepository bookmarkRepository;

    @Mock private UserReader userReader;

    @Mock private EmergencyRoomReader emergencyRoomReader;

    private EmergencyRoom expectedEmergencyRoom;
    private User expectedkUser;

    @BeforeEach
    void setup() {
        expectedEmergencyRoom = EmergencyRoomFixture.create();
        expectedkUser = UserFixture.createUserWithId();
    }

    @Test
    @DisplayName("유저Id, 응급실Id를 통해 즐겨찾기를 저장한다.")
    void givenUserIdEmergencyRoomId_whenCreateBookmark_thenShouldSaveBookmark() {
        // Given:
        Long emergencyRoomId = 1L;
        Long userId = 1L;

        // When
        when(emergencyRoomReader.read(emergencyRoomId)).thenReturn(expectedEmergencyRoom);
        when(userReader.read(userId)).thenReturn(expectedkUser);

        bookmarkAppender.save(emergencyRoomId, userId);

        // Then
        assertEquals(userId, expectedkUser.getId());
        verify(bookmarkRepository, times(1))
                .save(
                        argThat(
                                bookmark ->
                                        bookmark.getUser().equals(expectedkUser)
                                                && bookmark.getEmergencyRoom()
                                                        .equals(expectedEmergencyRoom)));
    }
}
