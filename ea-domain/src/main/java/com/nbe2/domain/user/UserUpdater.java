package com.nbe2.domain.user;

import com.nbe2.domain.auth.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.file.FileMetaData;

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

    public void update(User user, UserProfile profile) {
        user.update(UserProfile.of(profile.name(), profile.email(), passwordEncoder.encode(profile.password())));
        userRepository.save(user);
    }
}
