package com.nbe2.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckApi {
    @GetMapping
    public String healthCheck() {
        return "health check success";
    }
}
