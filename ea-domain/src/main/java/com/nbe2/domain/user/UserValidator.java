package com.nbe2.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.exception.AlreadyExistsEmailException;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(String email) {
        if (isEmailDuplicated(email)) {
            throw AlreadyExistsEmailException.EXCEPTION;
        }
    }

    private boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }
}
