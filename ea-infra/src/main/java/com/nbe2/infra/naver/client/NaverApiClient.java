package com.nbe2.infra.naver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbe2.infra.naver.config.NaverApiClientConfiguration;
import com.nbe2.infra.naver.dto.NaverDirectionsResponse;

@FeignClient(
        name = "naverApiClient",
        url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/",
        configuration = NaverApiClientConfiguration.class)
public interface NaverApiClient {

    @GetMapping(value = "/driving")
    NaverDirectionsResponse getRealTimeEmergencyData(
            @RequestParam String goal, @RequestParam String start);
    //            @RequestParam(required = false) int numOfRows);

}
