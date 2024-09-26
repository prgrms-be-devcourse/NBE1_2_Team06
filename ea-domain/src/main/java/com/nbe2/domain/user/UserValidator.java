package com.nbe2.domain.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.exception.AlreadyExistsEmailException;
import com.nbe2.domain.user.exception.HospitalRequiredException;
import com.nbe2.domain.user.exception.MedicalLicenseRequiredException;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(String email) {
        if (isEmailDuplicated(email)) {
            throw AlreadyExistsEmailException.EXCEPTION;
        }
    }

    public MedicalProfile validateMedicalProfile(
            Optional<Long> emergencyRoomId, Optional<Long> fileId) {
        return new MedicalProfile(
                emergencyRoomId.orElseThrow(() -> HospitalRequiredException.EXCEPTION),
                fileId.orElseThrow(() -> MedicalLicenseRequiredException.EXCEPTION));
    }

    private boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }
}
