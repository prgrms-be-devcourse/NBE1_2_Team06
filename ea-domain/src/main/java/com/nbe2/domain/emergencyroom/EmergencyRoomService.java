package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final EmergencyRoomClient emergencyRoomClient;

    public List<RealTimeEmergencyInfo> getRealTimeEmergencyData(String region, String subRegion) {
        return emergencyRoomClient.getRealTimeEmergencyData(region, subRegion);
    }
}
