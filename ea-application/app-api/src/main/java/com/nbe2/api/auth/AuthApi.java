package com.nbe2.api.auth;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.LoginRequest;
import com.nbe2.api.auth.dto.SignupRequest;
import com.nbe2.api.global.dto.Response;
import com.nbe2.api.global.util.CookieUtils;
import com.nbe2.domain.auth.AuthConstants;
import com.nbe2.domain.auth.AuthService;
import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.auth.UserPrincipal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<Void> signUp(
            @RequestBody SignupRequest signupRequest,
            @RequestParam(required = false) Optional<Long> emergencyRoomId,
            @RequestParam(required = false) Optional<Long> licenseId) {
        authService.signUp(signupRequest.toUserProfile(), emergencyRoomId, licenseId);
        return Response.success();
    }

    @PostMapping("/login")
    public ResponseEntity<Response<Void>> login(@RequestBody LoginRequest loginRequest) {
        Tokens tokens = authService.login(loginRequest.toLogin());
        HttpHeaders headers = createTokenHeaders(tokens);
        return ResponseEntity.ok().headers(headers).body(Response.success());
    }

    @DeleteMapping("/logout")
    public Response<Void> logout(@RequestBody UserPrincipal userPrincipal) {
        authService.logout(userPrincipal.userId());
        return Response.success();
    }

    private HttpHeaders createTokenHeaders(Tokens tokens) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie =
                CookieUtils.createHttpOnlyCookie(
                        AuthConstants.REFRESH_TOKEN_COOKIE_NAME,
                        tokens.refreshToken(),
                        AuthConstants.REFRESH_TOKEN_TTL);
        headers.set(HttpHeaders.AUTHORIZATION, tokens.accessToken());
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());
        return headers;
    }
}
