package com.nbe2.infra.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nbe2.infra.feign.config.FeignConfig;
import com.nbe2.infra.kakao.dto.KakaoTokenResponse;

@FeignClient(
        name = "kakao-auth-client",
        url = "https://kauth.kakao.com",
        configuration = FeignConfig.class)
public interface KakaoAuthClient {

    @PostMapping(
            "/oauth/token?client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&grant_type=authorization_code")
    KakaoTokenResponse auth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code);
}
