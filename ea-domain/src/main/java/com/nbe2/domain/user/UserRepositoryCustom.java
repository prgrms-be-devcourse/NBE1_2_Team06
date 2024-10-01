package com.nbe2.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Page<UserProfileWithLicense> findPageByApprovalStatus(
            ApprovalStatus approvalStatus, Pageable pageable);
}
