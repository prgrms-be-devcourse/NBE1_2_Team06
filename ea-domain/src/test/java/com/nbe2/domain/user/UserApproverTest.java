package com.nbe2.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserApproverTest {

    @InjectMocks private UserApprover userApprover;

    @Mock private UserRepository userRepository;

    @Test
    @DisplayName("사용자의 가입 상태를 변경한다.")
    void given_user_when_approve_then_approve_user() {
        // given
        User user = UserFixture.createPendingUser();

        // when
        userApprover.approve(user);

        // then
        verify(userRepository).save(any(User.class));
    }
}
