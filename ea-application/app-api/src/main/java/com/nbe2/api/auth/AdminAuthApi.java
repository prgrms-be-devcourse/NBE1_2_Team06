package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.PendingUserResponse;
import com.nbe2.api.auth.dto.TokensRequest;
import com.nbe2.api.global.dto.Response;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.AdminAuthService;
import com.nbe2.domain.auth.TokenManager;
import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.user.UserProfileWithLicense;

@RestController
@RequestMapping("/api/v1/auth/admin")
@RequiredArgsConstructor
public class AdminAuthApi {

    private final AdminAuthService adminAuthService;
    private final TokenManager tokenManager;

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

    @PostMapping("/reissue")
    public Response<Tokens> reissue(@RequestBody TokensRequest tokenRequestDto) {
        return Response.success(tokenManager.updateToken(tokenRequestDto.to()));
    }
}
