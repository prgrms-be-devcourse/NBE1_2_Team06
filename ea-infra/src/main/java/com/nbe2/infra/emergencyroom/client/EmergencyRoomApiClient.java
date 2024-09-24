package com.nbe2.infra.emergencyroom.client;

import static com.nbe2.infra.openapi.properties.OpenApiProperties.*;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.emergencyroom.EmergencyRoomClient;
import com.nbe2.domain.emergencyroom.RealTimeEmergencyInfo;
import com.nbe2.infra.openapi.client.OpenApiFeignClient;
import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmergencyRoomApiClient implements EmergencyRoomClient {

    private final OpenApiFeignClient openApiFeignClient;

    @Override
    public List<RealTimeEmergencyInfo> getRealTimeEmergencyData(String region, String subRegion) {
        return openApiFeignClient
                .getRealTimeEmergencyData(region, subRegion, NUM_OF_ROWS)
                .getItems()
                .stream()
                .map(RealTimeEmergencyDataResponse::toRealTimeEmergencyInfo)
                .toList();
    }
}
