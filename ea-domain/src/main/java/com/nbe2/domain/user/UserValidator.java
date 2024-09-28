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
        if (isEmailExists(email)) {
            throw AlreadyExistsEmailException.EXCEPTION;
        }
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isMedicalUser(Optional<Long> emergencyId, Optional<Long> licenseId) {
        return emergencyId.isPresent() || licenseId.isPresent();
    }

    public MedicalProfile validate(Optional<Long> emergencyRoomId, Optional<Long> fileId) {
        return new MedicalProfile(
                emergencyRoomId.orElseThrow(() -> HospitalRequiredException.EXCEPTION),
                fileId.orElseThrow(() -> MedicalLicenseRequiredException.EXCEPTION));
    }
}
