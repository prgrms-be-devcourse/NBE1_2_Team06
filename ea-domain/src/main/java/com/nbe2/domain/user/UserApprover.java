package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserApprover {

    private final UserRepository userRepository;

    public void approve(User user) {
        user.approve();
        userRepository.save(user);
    }
}
