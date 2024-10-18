package com.nbe2.domain.bookmark;

import static org.junit.jupiter.api.Assertions.*;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class BookmarkReaderTest {

	@InjectMocks
	private BookmarkReader bookmarkReader;

	@Mock
	private BookmarkRepository bookmarkRepository;

	private EmergencyRoom expectedEmergencyRoom;
	private User expectedUser;
	@BeforeEach
	void setup(){
		expectedEmergencyRoom = EmergencyRoomFixture.create();
		expectedUser = UserFixture.createUserWithId();
	}

}