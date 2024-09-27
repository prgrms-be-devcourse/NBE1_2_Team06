package com.nbe2.domain.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserApprover;
import com.nbe2.domain.user.UserProfileWithLicense;
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

    @Transactional(readOnly = true)
    public PageResult<UserProfileWithLicense> searchPendingUsers(Page page) {
        return userReader.read(PagingUtil.toPageRequest(page));
    }
}
