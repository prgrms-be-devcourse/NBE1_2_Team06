package com.nbe2.infra.emergencyroom.client;

import static com.nbe2.infra.openapi.properties.OpenApiProperties.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoomClient;
import com.nbe2.domain.emergencyroom.EmergencyRoomInfo;
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo;
import com.nbe2.domain.emergencyroom.Region;
import com.nbe2.infra.openapi.client.OpenApiFeignClient;
import com.nbe2.infra.openapi.dto.AllEmergencyRoomResponse;
import com.nbe2.infra.openapi.dto.EmergencyRoomResponse;
import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse;
import com.nbe2.infra.openapi.dto.TraumaCenterResponse;

@Component
@RequiredArgsConstructor
public class EmergencyRoomApiClient implements EmergencyRoomClient {

    private final OpenApiFeignClient openApiFeignClient;

    @Override
    public List<RealTimeEmergencyRoomInfo> getRealTimeInfo(Region region) {
        return openApiFeignClient
                .getRealTimeEmergencyData(region.region(), region.subRegion(), NUM_OF_ROWS)
                .getItems()
                .stream()
                .map(RealTimeEmergencyDataResponse::toRealTimeEmergencyInfo)
                .toList();
    }

    @Override
    public List<EmergencyRoomInfo> getEmergencyRoomInfoData() {
        return Stream.concat(getEmergencyData().stream(), getTraumaCenterData().stream())
                .collect(Collectors.toList());
    }

    private List<EmergencyRoomInfo> getEmergencyData() {
        return getAllEmergencyRoomData().stream()
                .map(ed -> openApiFeignClient.getEmergencyInfoData(ed.hpid(), 1, 1000).getItems())
                .map(EmergencyRoomResponse::toEmergencyRoomInfo)
                .toList();
    }

    private List<EmergencyRoomInfo> getTraumaCenterData() {
        List<AllEmergencyRoomResponse> traumaCenterData = getAllTraumaCenterData();
        return traumaCenterData.stream()
                .map(tc -> openApiFeignClient.getTraumaCenterDataInfo(tc.hpid(), 1, 20).getItems())
                .map(TraumaCenterResponse::toEmergencyRoomInfo)
                .toList();
    }

    private List<AllEmergencyRoomResponse> getAllTraumaCenterData() {
        return openApiFeignClient.getAllTraumaCenterData(1, 20).getItems();
    }

    private List<AllEmergencyRoomResponse> getAllEmergencyRoomData() {
        return openApiFeignClient.getAllEmergencyData(1, 1000).getItems();
    }
}
