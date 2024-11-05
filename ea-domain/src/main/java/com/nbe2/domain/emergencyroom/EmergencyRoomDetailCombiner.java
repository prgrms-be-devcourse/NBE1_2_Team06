package com.nbe2.domain.emergencyroom;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmergencyRoomDetailCombiner {
    private final EmergencyRoomReader emergencyRoomReader;
    private final RealTimeEmergencyRoomInfoCacheManager realTimeEmergencyRoomInfoCacheManager;
    private final RealTimeEmergencyRoomInfoFetcher realTimeEmergencyRoomInfoFetcher;
    private final DistanceCalculator distanceCalculator;

    public EmergencyRoomDetailInfo combineEmergencyRoomDetailInfo(
            String hospitalId, Coordinate coordinate) {
        boolean isOverBed = false;
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(hospitalId);

        RealTimeEmergencyRoomInfo realTimeEmergencyRoomInfo =
                realTimeEmergencyRoomInfoCacheManager
                        .getInfo(emergencyRoom.getHpId())
                        .orElseGet(
                                () ->
                                        realTimeEmergencyRoomInfoFetcher
                                                .reloadRealTimeEmergencyRooms(
                                                        coordinate, hospitalId));

        RealTimeEmergencyRoomWithDistance realTimeEmergencyRoomWithDistance =
                distanceCalculator.calculateDistance(coordinate, realTimeEmergencyRoomInfo);

        // 가용 병상이 전체 병상을 초과 했을 때
        if (emergencyRoom.getEmergencyRoomBedCount() < realTimeEmergencyRoomInfo.availableBeds()) {
            isOverBed = true;
        }
        EmergencyRoomDetailInfo emergencyRoomDetailInfo =
                EmergencyRoomDetailInfo.create(
                        emergencyRoom, realTimeEmergencyRoomWithDistance, isOverBed);
        return emergencyRoomDetailInfo;
    }
}
