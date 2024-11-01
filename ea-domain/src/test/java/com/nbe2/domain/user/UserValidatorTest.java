package com.nbe2.domain.user;

import static com.nbe2.domain.global.TestConstants.EMAIL;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.exception.AlreadyExistsEmailException;

@ExtendWith(value = MockitoExtension.class)
class UserValidatorTest {

    @InjectMocks private UserValidator userValidator;

    @Mock private UserRepository userRepository;

    @Nested
    @DisplayName("이메일 유효성을 검증한다.")
    class EmailValidationTest {

        @Test
        @DisplayName("유효한 이메일 전달 시 예외가 발생하지 않는다.")
        void given_valid_email_when_validate_user_then_should_not_throw_exception() {
            // given
            String email = EMAIL;

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);

            // then
            assertDoesNotThrow(() -> userValidator.validate(email));
        }

        @Test
        @DisplayName("이미 존재하는 이메일 전달 시 예외가 발생한다.")
        void given_invalid_email_when_validate_user_then_should_throw_exception() {
            // given
            String email = EMAIL;

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(true);

            // then
            assertThrows(AlreadyExistsEmailException.class, () -> userValidator.validate(email));
        }
    }
}
