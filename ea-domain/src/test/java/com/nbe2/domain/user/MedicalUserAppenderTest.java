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

import com.nbe2.domain.auth.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class MedicalUserAppenderTest {

    @InjectMocks private MedicalUserAppender medicalUserAppender;

    @Mock private PasswordEncoder passwordEncoder;

    @Mock private UserRepository userRepository;

    @Test
    @DisplayName("의료 관계자를 저장한다.")
    void given_user_and_medical_profile_when_create_medical_user_then_should_save_medical_user() {
        // given
        String encodedPassword = "encoded password";
        UserProfile userProfile = UserFixture.createUserProfile();
        MedicalProfile medicalProfile = UserFixture.createMedicalProfile();

        // when
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        medicalUserAppender.append(userProfile, medicalProfile);

        // then
        verify(userRepository).save(any(User.class));
    }
}
