package com.nbe2.domain.bookmark;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookmarkAppenderTest {

	@InjectMocks
	private BookmarkAppender bookmarkAppender;

	@Mock
	private BookmarkRepository bookmarkRepository;

	@Mock
	private UserReader userReader;

	@Mock
	private EmergencyRoomReader emergencyRoomReader;

	@Test
	@DisplayName("응급실 Id와 회원 Id를 통해 즐겨찾기를 저장한다")
	void given_emergencyRoomId_userId_when_create_bookmark_then_save_bookmark(){
		//given
		long emergencyRoomId = 1;
		long userId = 1;
		EmergencyRoom emergencyRoom = emergencyRoomReader.read(emergencyRoomId);
		User user = userReader.read(userId);

		when(emergencyRoomReader.read(emergencyRoomId)).thenReturn(emergencyRoom);
		when(userReader.read(userId)).thenReturn(user);
		when(bookmarkRepository.save(any(Bookmark.class))).thenReturn(new Bookmark());

		// When
		bookmarkAppender.save(emergencyRoomId, userId);

		// Then
		// verify(emergencyRoomReader).read(emergencyRoomId);
		// verify(userReader).read(userId);
		verify(bookmarkRepository).save(any(Bookmark.class));
	}

}