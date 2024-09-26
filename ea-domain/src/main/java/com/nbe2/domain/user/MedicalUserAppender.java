package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.PasswordEncoder;
import com.nbe2.domain.emergencyroom.EmergencyRoom;

@Component
@RequiredArgsConstructor
public class MedicalUserAppender {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // @TODO EmergencyRoom, File 조회해서 (~~Reader) 전달해야 함
    public void append(UserProfile userProfile, MedicalProfile medicalProfile) {
        userRepository.save(createMedicalUser(userProfile, null)); // 임시 방편....
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
