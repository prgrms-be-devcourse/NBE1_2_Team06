package com.nbe2.infra.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbe2.infra.kakao.config.KakaoFeignConfig;
import com.nbe2.infra.kakao.dto.KakaoApiResponse;
import com.nbe2.infra.kakao.dto.KakaoRegionResponse;

@FeignClient(
        name = "kakao-client",
        url = "https://kapi.kakao.com",
        configuration = KakaoFeignConfig.class)
public interface KakaoClient {

    @GetMapping("/v2/local/v2/local/geo/coord2regioncode.json")
    KakaoApiResponse<KakaoRegionResponse> getRegionData(
            @RequestParam Double x, @RequestParam Double y);
}
