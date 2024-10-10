package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.PasswordEncoder;
import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.user.exception.InvalidPasswordException;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void requestMedicalRole(User user, EmergencyRoom emergencyRoom, FileMetaData license) {
        MedicalPersonInfo medicalPersonInfo = MedicalPersonInfo.of(user, emergencyRoom, license);
        user.assignMedicalRole(medicalPersonInfo);
        userRepository.save(user);
    }

    public void update(User user, UpdateProfile profile) {
        user.update(profile);
        userRepository.save(user);
    }

    public void update(User user, UpdatePassword password) {
        if (passwordEncoder.isPasswordUnmatched(password.previous(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        user.changePassword(passwordEncoder.encode(password.toChange()));
        userRepository.save(user);
    }
}
