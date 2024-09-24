package com.nbe2.infra.openapi.config;

import org.springframework.context.annotation.Bean;

public class OpenApiFeignClientConfiguration {

    @Bean
    public OpenApiRequestInterceptor openApiRequestInterceptor() {
        return new OpenApiRequestInterceptor();
    }
}
