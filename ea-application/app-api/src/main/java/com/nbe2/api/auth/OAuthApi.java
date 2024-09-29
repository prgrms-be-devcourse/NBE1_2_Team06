package com.nbe2.api.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.auth.dto.OAuthConnectionResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.api.global.util.TokenUtils;
import com.nbe2.domain.auth.OAuthService;
import com.nbe2.domain.auth.Tokens;

@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class OAuthApi {

    private final OAuthService oAuthService;

    @GetMapping("/login/connection")
    public Response<OAuthConnectionResponse> getOAuthConnection() {
        OAuthConnectionResponse oAuthConnectionResponse =
                OAuthConnectionResponse.from(oAuthService.getConnectionUrl());
        return Response.success(oAuthConnectionResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<Response<Void>> login(@RequestParam String code) {
        Tokens tokens = oAuthService.login(code);
        HttpHeaders header = TokenUtils.createTokenHeaders(tokens);
        return ResponseEntity.ok().headers(header).body(Response.success());
    }
}
