package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.OAuthProfile;
import com.nbe2.domain.auth.PasswordEncoder;
import com.nbe2.domain.emergencyroom.EmergencyRoom;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void append(UserProfile userProfile) {
        userRepository.save(
                User.createNormalUserOf(
                        userProfile.name(),
                        userProfile.email(),
                        passwordEncoder.encode(userProfile.password())));
    }

    // @TODO EmergencyRoom, File 조회해서 (~~Reader) 전달해야 함
    public void append(UserProfile userProfile, MedicalProfile medicalProfile) {
        userRepository.save(createMedicalUser(userProfile, null)); // 임시 방편....
    }

    public void append(OAuthProfile oAuthProfile) {
        userRepository.save(
                User.createNormalUserOf(oAuthProfile.getNickname(), oAuthProfile.getEmail(), null));
    }

    private User createMedicalUser(UserProfile userProfile, EmergencyRoom emergencyRoom) {
        User user =
                User.createMedicalUserOf(
                        userProfile.name(),
                        userProfile.email(),
                        passwordEncoder.encode(userProfile.password()));
        MedicalPersonInfo medicalPersonInfo = MedicalPersonInfo.create();
        user.assignMedicalPerson(medicalPersonInfo);
        medicalPersonInfo.assignParent(user, emergencyRoom);

        return user;
    }
}
