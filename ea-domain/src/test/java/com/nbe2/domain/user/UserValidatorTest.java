package com.nbe2.domain.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.exception.AlreadyExistsEmailException;
import com.nbe2.domain.user.exception.HospitalRequiredException;
import com.nbe2.domain.user.exception.MedicalLicenseRequiredException;

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
            String email = "email@email.com";

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(false);

            // then
            assertDoesNotThrow(() -> userValidator.validate(email));
        }

        @Test
        @DisplayName("이미 존재하는 이메일 전달 시 예외가 발생한다.")
        void given_invalid_email_when_validate_user_then_should_throw_exception() {
            // given
            String email = "email@email.com";

            // when
            when(userRepository.existsByEmail(anyString())).thenReturn(true);

            // then
            assertThrows(AlreadyExistsEmailException.class, () -> userValidator.validate(email));
        }
    }

    @Nested
    @DisplayName("의료 관계자 관련 정보를 검증한다.")
    class MedicalProfileValidationTest {

        @Test
        @DisplayName("병원 ID, 면허 ID가 전달되면 의료 관계자이다.")
        void given_emergency_room_or_license_id_then_medical_user() {
            // given
            Optional<Long> emergencyRoomId = Optional.of(1L);
            Optional<Long> licenseId = Optional.of(2L);

            // then
            assertTrue(userValidator.isMedicalUser(emergencyRoomId, licenseId));
        }

        @Test
        @DisplayName("병원 ID, 면허 ID 모두 전달되지 않으면 일반 사용자이다.")
        void given_empty_id_then_normal_user() {
            // given
            Optional<Long> emergencyRoomId = Optional.empty();
            Optional<Long> licenseId = Optional.empty();

            // then
            assertFalse(userValidator.isMedicalUser(emergencyRoomId, licenseId));
        }

        @Test
        @DisplayName("유효한 병원 ID, 면허 파일 ID 전달 시 예외가 발생하지 않는다.")
        void given_valid_profile_when_validate_medical_profile_then_should_not_throw_exception() {
            // given
            Optional<Long> emegencyId = Optional.of(1L);
            Optional<Long> medicalId = Optional.of(2L);

            // then
            assertDoesNotThrow(() -> userValidator.validate(emegencyId, medicalId));
        }

        @Test
        @DisplayName("병원 ID를 전달하지 않으면 예외가 발생한다.")
        void
                given_empty_emergency_room_id_when_validate_medical_profile_then_should_throw_exception() {
            // given
            Optional<Long> emergencyId = Optional.empty();
            Optional<Long> medicalId = Optional.of(1L);

            // then
            assertThrows(
                    HospitalRequiredException.class,
                    () -> userValidator.validate(emergencyId, medicalId));
        }

        @Test
        @DisplayName("면허 파일 ID를 전달하지 않으면 예외가 발생한다.")
        void given_empty_file_id_when_validate_medical_profile_then_should_throw_exception() {
            // given
            Optional<Long> emergencyId = Optional.of(1L);
            Optional<Long> medicalId = Optional.empty();

            // then
            assertThrows(
                    MedicalLicenseRequiredException.class,
                    () -> userValidator.validate(emergencyId, medicalId));
        }
    }
}
