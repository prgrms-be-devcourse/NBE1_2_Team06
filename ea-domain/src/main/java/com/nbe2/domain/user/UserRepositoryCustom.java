package com.nbe2.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Page<UserProfileWithLicense> findPageBySignupStatus(
            SignupStatus signupStatus, Pageable pageable);
}
