package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.SignupRequest;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.auth.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("signup")
    public Response<Void> signUp(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest.toUserProfile());
        return Response.success();
    }
}
