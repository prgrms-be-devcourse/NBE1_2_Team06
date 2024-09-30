package com.nbe2.infra.naver.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.nbe2.infra.openapi.config.OpenApiFeignClientConfiguration;

@FeignClient(
        name = "naverApiClient",
        url = "https://openapi.naver.com",
        configuration = OpenApiFeignClientConfiguration.class)
public interface NaverApiClient {}
