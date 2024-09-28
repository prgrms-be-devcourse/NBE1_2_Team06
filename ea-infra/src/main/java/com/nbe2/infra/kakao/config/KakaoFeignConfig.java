package com.nbe2.infra.kakao.config;

import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean
    public KakaoFeignRequestInterceptor kakaoFeignRequestInterceptor() {
        return new KakaoFeignRequestInterceptor();
    }
}
