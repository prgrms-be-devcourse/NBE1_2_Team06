package com.nbe2.infra.openapi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenApiProperties {

    public static String SERVICE_KEY;

    public static int NUM_OF_ROWS = 10;

    @Value("${openapi.service-key}")
    public void setServiceKey(String serviceKey) {
        SERVICE_KEY = serviceKey;
    }
}
