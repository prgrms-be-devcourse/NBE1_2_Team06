package com.nbe2.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.auth.PasswordEncoder;
import com.nbe2.domain.user.exception.InvalidPasswordException;

@ExtendWith(MockitoExtension.class)
class UserUpdaterTest {

    @InjectMocks private UserUpdater userUpdater;

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserRepository userRepository;

    @Test
    @DisplayName("사용자 프로필을 수정한다.")
    void givenNewProfile_whenUpdateProfile_thenUpdatingSuccess() {
        // given
        User user = UserFixture.createUserWithId();
        UpdateProfile newProfile = new UpdateProfile("new name", "new email");

        // when
        userUpdater.update(user, newProfile);

        // then
        verify(userRepository).save(user);
        assertEquals(newProfile.name(), user.getName());
        assertEquals(newProfile.email(), user.getEmail());
    }

    @Test
    @DisplayName("이전 비밀번호가 일치하면 비밀번호를 수정한다.")
    void givenNewPassword_whenChangePassword_thenChangingSuccess() {
        // given
        User user = UserFixture.createUserWithId();
        UpdatePassword password = new UpdatePassword(user.getPassword(), "new password");
        String encoded = "encoded password";

        // when
        when(passwordEncoder.isPasswordUnmatched(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn(encoded);
        userUpdater.update(user, password);

        // then
        verify(userRepository).save(user);
        assertEquals(encoded, user.getPassword());
    }

    @Test
    @DisplayName("이번 비밀번호와 다르면 예외가 발생한다.")
    void givenNewPassword_whenPreviousUnmatches_thenShouldThrowException() {
        // given
        User user = UserFixture.createUserWithId();
        UpdatePassword password = new UpdatePassword(user.getPassword(), "new password");

        // when
        when(passwordEncoder.isPasswordUnmatched(anyString(), anyString())).thenReturn(true);

        // then
        assertThrows(InvalidPasswordException.class, () -> userUpdater.update(user, password));
    }
}
