package com.nbe2.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.auth.AuthFixture;
import com.nbe2.domain.auth.OAuthProfile;
import com.nbe2.domain.auth.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserAppenderTest {

    @InjectMocks private UserAppender userAppender;

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserRepository userRepository;

    @Test
    @DisplayName("이름, 이메일, 비밀번호를 통해 회원을 저장한다.")
    void given_name_email_password_when_create_user_then_should_save_user() {
        // given
        String encodedPassword = "encoded password";
        UserProfile userProfile = UserFixture.createUserProfile();

        // when
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        userAppender.append(userProfile);

        // then
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("소셜 계정을 연동한 사용자를 저장한다.")
    void given_oauth_profile_when_create_oauth_user_then_should_save_oauth_user() {
        // given
        OAuthProfile oAuthProfile = AuthFixture.createOAuthProfile();

        // when
        userAppender.append(oAuthProfile);

        // then
        verify(userRepository).save(any(User.class));
    }
}
