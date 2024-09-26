package com.nbe2.domain.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserAppender;
import com.nbe2.domain.user.UserProfile;
import com.nbe2.domain.user.UserValidator;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserAppender userAppender;

    @Transactional
    public void signUp(UserProfile userProfile) {
        userValidator.validate(userProfile.email());
        userAppender.append(
                userProfile.name(),
                userProfile.email(),
                passwordEncoder.encode(userProfile.password()));
    }
}
