package com.nbe2.domain.bookmark;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;*/

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.nbe2.domain.global.TestConstants;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {
	@InjectMocks
	private BookmarkService bookmarkService;

	@Mock
	private BookmarkReader bookmarkReader;

	@Mock
	private UserReader userReader;

	@Test
	@DisplayName("즐겨찾기를 조회한다.")
	void givenUserId_whenIdIsValid_thenReturnBookmark(){

	}
}
