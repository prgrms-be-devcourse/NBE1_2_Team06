package com.nbe2.domain.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.MedicalProfile;
import com.nbe2.domain.user.MedicalUserAppender;
import com.nbe2.domain.user.UserAppender;
import com.nbe2.domain.user.UserProfile;
import com.nbe2.domain.user.UserValidator;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserValidator userValidator;
    private final UserAppender userAppender;
    private final MedicalUserAppender medicalUserAppender;

    @Transactional
    public void signUp(
            UserProfile userProfile, Optional<Long> emergencyRoomId, Optional<Long> fileId) {
        userValidator.validate(userProfile.email());

        if (emergencyRoomId.isPresent() || fileId.isPresent()) {
            MedicalProfile medicalProfile =
                    userValidator.validateMedicalProfile(emergencyRoomId, fileId);
            medicalUserAppender.append(userProfile, medicalProfile);
        } else {
            userAppender.append(userProfile);
        }
    }
}
