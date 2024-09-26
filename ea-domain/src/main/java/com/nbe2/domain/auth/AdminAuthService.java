package com.nbe2.domain.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserApprover;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final UserReader userReader;
    private final UserApprover userApprover;

    @Transactional
    public void approveSignup(Long userId) {
        User user = userReader.read(userId);
        userApprover.approve(user);
    }
}
