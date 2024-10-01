package com.nbe2.infra.naver.config;

import org.springframework.context.annotation.Bean;

public class NaverApiClientConfiguration {

    @Bean
    public NaverRequestInterceptor naverRequestInterceptor() {
        return new NaverRequestInterceptor();
    }
}
