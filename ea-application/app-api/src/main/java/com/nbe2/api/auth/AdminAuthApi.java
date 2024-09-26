package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.auth.AdminAuthService;

@RestController
@RequestMapping("/api/v1/auth/admin")
@RequiredArgsConstructor
public class AdminAuthApi {

    private final AdminAuthService adminAuthService;

    @PatchMapping("/pendings")
    public Response<Void> approveSignupRequest(@RequestParam Long userId) {
        adminAuthService.approveSignup(userId);
        return Response.success();
    }
}
