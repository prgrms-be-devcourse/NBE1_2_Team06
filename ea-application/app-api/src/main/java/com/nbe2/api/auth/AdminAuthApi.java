package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.PendingUserResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.AdminAuthService;
import com.nbe2.domain.user.UserProfileWithLicense;

@RestController
@RequestMapping("/api/v1/auth/admin")
@RequiredArgsConstructor
public class AdminAuthApi {

    private final AdminAuthService adminAuthService;

    @GetMapping("/pendings")
    public Response<PageResult<PendingUserResponse>> searchPendingUsers(@PageDefault Page page) {
        PageResult<UserProfileWithLicense> userPageResult =
                adminAuthService.searchPendingUsers(page);
        return Response.success(userPageResult.map(PendingUserResponse::from));
    }

    @PatchMapping("/pendings")
    public Response<Void> approveSignupRequest(@RequestParam Long userId) {
        adminAuthService.approveSignup(userId);
        return Response.success();
    }
}
