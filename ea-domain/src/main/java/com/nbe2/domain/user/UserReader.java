package com.nbe2.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.user.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User read(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User read(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public PageResult<UserProfileWithLicense> read(Pageable pageable) {
        Page<UserProfileWithLicense> userPage =
                userRepository.findPageByApprovalStatus(ApprovalStatus.PENDING, pageable);
        return new PageResult<>(
                userPage.getContent(), userPage.getTotalPages(), userPage.hasNext());
    }
}
