package com.nbe2.infra.kakao.config;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KakaoFeignRequestInterceptor implements RequestInterceptor {

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "KakaoAK " + restApiKey);
    }
}
