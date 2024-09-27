package com.nbe2.infra.openapi.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbe2.infra.openapi.config.OpenApiFeignClientConfiguration;
import com.nbe2.infra.openapi.dto.*;

@FeignClient(
        name = "openApiFeignClient",
        url = "https://apis.data.go.kr/B552657/ErmctInfoInqireService",
        configuration = OpenApiFeignClientConfiguration.class)
public interface OpenApiFeignClient {

    @GetMapping(value = "/getEmrrmRltmUsefulSckbdInfoInqire")
    OpenApiResponse<List<RealTimeEmergencyDataResponse>> getRealTimeEmergencyData(
            @RequestParam String STAGE1,
            @RequestParam(required = false) String STAGE2,
            @RequestParam(required = false) int numOfRows);

    @GetMapping(value = "/getEgytListInfoInqire")
    OpenApiResponse<List<AllEmergencyRoomResponse>> getAllEmergencyData(
            @RequestParam(value = "pageStartNum", required = false) int pageStartNum,
            @RequestParam(value = "numOfRows", required = false) int numOfRows);

    @GetMapping(value = "/getEgytBassInfoInqire")
    OpenApiResponse<EmergencyRoomResponse> getEmergencyInfoData(
            @RequestParam(value = "HPID", required = false) String hpid,
            @RequestParam(value = "pageNo", required = false) int pageStartNum,
            @RequestParam(value = "numOfRows", required = false) int numOfRows);

    @GetMapping(value = "/getStrmListInfoInqire")
    OpenApiResponse<List<AllEmergencyRoomResponse>> getAllTraumaCenterData(
            @RequestParam(value = "pageNo", required = false) int pageStartNum,
            @RequestParam(value = "numOfRows", required = false) int numOfRows);

    @GetMapping(value = "/getEgytBassInfoInqire")
    OpenApiResponse<TraumaCenterResponse> getTraumaCenterDataInfo(
            @RequestParam(value = "HPID", required = false) String hpid,
            @RequestParam(value = "pageNo", required = false) int pageStartNum,
            @RequestParam(value = "numOfRows", required = false) int numOfRows);
}
