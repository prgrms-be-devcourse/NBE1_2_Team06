package com.nbe2.infra.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.nbe2.infra.feign.config.FeignConfig;
import com.nbe2.infra.kakao.dto.KakaoProfileResponse;

@FeignClient(
        name = "kakao-profile-client",
        url = "https://kapi.kakao.com",
        configuration = FeignConfig.class)
public interface KakaoProfileClient {

    @GetMapping("/v1/oidc/userinfo")
    KakaoProfileResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
