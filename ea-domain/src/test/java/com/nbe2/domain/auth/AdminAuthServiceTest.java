package com.nbe2.domain.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserApprover;
import com.nbe2.domain.user.UserFixture;
import com.nbe2.domain.user.UserReader;
import com.nbe2.domain.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class AdminAuthServiceTest {

    @InjectMocks private AdminAuthService adminAuthService;

    @Mock private UserReader userReader;

    @Mock private UserApprover userApprover;

    @Test
    @DisplayName("승인 대기 상태의 회원을 가입 승인한다.")
    void given_user_id_when_user_is_pending_then_should_approve_user() {
        // given
        long userId = 1L;
        User user = UserFixture.createPendingUser();

        // when
        when(userReader.read(anyLong())).thenReturn(user);
        adminAuthService.approveSignup(userId);

        // then
        verify(userApprover).approve(user);
    }

    @Test
    @DisplayName("존재하지 않는 회원을 승인 시도할 시 예외가 발생한다.")
    void given_user_id_when_user_is_not_exists_then_should_throw_exception() {
        // given
        long userId = 1L;

        // when
        doThrow(UserNotFoundException.class).when(userReader).read(anyLong());

        // then
        assertThrows(UserNotFoundException.class, () -> adminAuthService.approveSignup(userId));
        verify(userApprover, never()).approve(any(User.class));
    }
}
