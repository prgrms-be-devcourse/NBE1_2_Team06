package com.nbe2.infra.openapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbe2.infra.openapi.config.OpenApiFeignClientConfiguration;
import com.nbe2.infra.openapi.dto.OpenApiResponse;
import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse;

@FeignClient(
        name = "openApiFeignClient",
        url = "https://apis.data.go.kr/B552657/ErmctInfoInqireService",
        configuration = OpenApiFeignClientConfiguration.class)
public interface OpenApiFeignClient {

    @GetMapping(value = "/getEmrrmRltmUsefulSckbdInfoInqire")
    OpenApiResponse<RealTimeEmergencyDataResponse> getRealTimeEmergencyData(
            @RequestParam String STAGE1,
            @RequestParam(required = false) String STAGE2,
            @RequestParam(required = false) int numOfRows);
}
