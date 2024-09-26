package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final RealTimeEmergencyRoomReader emergencyRoomClient;
    private final CoordinateToRegionConverter coordinateConverter;
    private final DistanceCalculator distanceCalculator;

    // 1. 실시간 응급실 정보 조회
    // 2. 조회된 응글실의 기관 코드를 통해 응급실 상세 정보 조회
    // 3. 각 응급실의 거리 조회
    // 4. 거리별로 정렬하여 사용자에게 제공
    public List<RealTimeEmergencyRoomWithDistance> getRealTimeEmergencyRooms(
            Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyInfo> realTimeEmergencyInfos =
                emergencyRoomClient.getRealTimeEmergencyData(region);
        return distanceCalculator.calculate(realTimeEmergencyInfos, currentCoordinate);
    }
}
