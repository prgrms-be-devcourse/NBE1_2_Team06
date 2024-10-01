package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.OAuthProfile;
import com.nbe2.domain.auth.PasswordCryptor;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final PasswordCryptor passwordCryptor;
    private final UserRepository userRepository;

    public void append(UserProfile userProfile) {
        userRepository.save(
                User.of(
                        userProfile.name(),
                        userProfile.email(),
                        passwordCryptor.encode(userProfile.password())));
    }

    public void append(OAuthProfile oAuthProfile) {
        userRepository.save(User.of(oAuthProfile.getNickname(), oAuthProfile.getEmail(), null));
    }
}
