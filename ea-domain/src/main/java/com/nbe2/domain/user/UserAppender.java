package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAppender {

    private final UserRepository userRepository;

    public void append(String name, String email, String password) {
        userRepository.save(User.createNormalUserOf(name, email, password));
    }
}
