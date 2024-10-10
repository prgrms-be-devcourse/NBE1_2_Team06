package com.nbe2.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.global.TestConstants;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks private UserService userService;

    @Mock private UserReader userReader;

    @Mock private UserUpdater userUpdater;

    @Test
    @DisplayName("사용자 프로필을 조회한다.")
    void givenUserId_whenIdIsValid_thenReturnProfile() {
        // given
        long userId = TestConstants.ID;
        User user = UserFixture.createUserWithId();
        MyProfile expected = MyProfile.from(user);

        // when
        when(userReader.read(anyLong())).thenReturn(user);
        MyProfile actual = userService.getMyProfile(userId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("사용자 프로필을 수정한다.")
    void givenNewProfile_whenUpdateProfile_thenUpdatingSuccess() {
        // given
        long userId = TestConstants.ID;
        User user = UserFixture.createUserWithId();
        UpdateProfile profile = new UpdateProfile(TestConstants.NAME, TestConstants.EMAIL);

        // when
        when(userReader.read(anyLong())).thenReturn(user);
        userService.updateProfile(userId, profile);

        // then
        verify(userUpdater).update(user, profile);
    }

    @Test
    @DisplayName("사용자 비밀번호를 수정한다.")
    void givenNewPassword_whenChangePassword_thenChangingSuccess() {
        // given
        long userId = TestConstants.ID;
        User user = UserFixture.createUserWithId();
        UpdatePassword password = new UpdatePassword("previous", "toChange");

        // when
        when(userReader.read(anyLong())).thenReturn(user);
        userService.changePassword(userId, password);

        // then
        verify(userUpdater).update(user, password);
    }
}
