package com.nbe2.api.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.LoginRequest;
import com.nbe2.api.auth.dto.SignupRequest;
import com.nbe2.api.auth.dto.TokensRequest;
import com.nbe2.api.global.dto.Response;
import com.nbe2.api.global.util.TokenUtils;
import com.nbe2.domain.auth.AuthService;
import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.auth.UserPrincipal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<Void> signUp(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest.toUserProfile());
        return Response.success();
    }

    @PostMapping("/login")
    public ResponseEntity<Response<Void>> login(@RequestBody LoginRequest loginRequest) {
        Tokens tokens = authService.login(loginRequest.toLogin());
        HttpHeaders headers = TokenUtils.createTokenHeaders(tokens);
        return ResponseEntity.ok().headers(headers).body(Response.success());
    }

    @DeleteMapping("/logout")
    public Response<Void> logout(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        authService.logout(userPrincipal.userId());
        return Response.success();
    }

    @PostMapping("/reissue")
    public Response<Tokens> reissue(@RequestBody TokensRequest tokenRequestDto) {
        return Response.success(authService.updateToken(tokenRequestDto.to()));
    }
}
