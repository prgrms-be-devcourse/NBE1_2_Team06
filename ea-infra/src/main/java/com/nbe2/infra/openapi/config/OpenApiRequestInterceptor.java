package com.nbe2.infra.openapi.config;

import org.springframework.beans.factory.annotation.Value;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OpenApiRequestInterceptor implements RequestInterceptor {

    @Value("${openapi.service-key}")
    private String serviceKey; // application.yml이나 properties 파일에 설정된 API 키를 불러옵니다.

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json"); // Accept 헤더 추가
        template.query("serviceKey", serviceKey); // serviceKey 파라미터 추가
    }
}
