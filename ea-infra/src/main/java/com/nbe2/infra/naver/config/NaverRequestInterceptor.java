package com.nbe2.infra.naver.config;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class NaverRequestInterceptor implements RequestInterceptor {

    @Value("${naver.directions-clinet-id}")
    private String clientKey;

    @Value("${naver.directions-secret-key}")
    private String serviceKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json"); // Accept 헤더 추가
        template.header("x-ncp-apigw-api-key-id", clientKey); // Accept 헤더 추가
        template.header("x-ncp-apigw-api-key", serviceKey); // Accept 헤더 추가
    }
}
