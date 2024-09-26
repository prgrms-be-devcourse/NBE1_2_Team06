package com.nbe2.domain.emergencyroom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DistanceCalculator {

    private final EmergencyRoomReader emergencyRoomReader;

    public List<RealTimeEmergencyRoomWithDistance> calculate(
            List<RealTimeEmergencyInfo> realTimeEmergencyInfos, Coordinate currentCoordinate) {
        List<RealTimeEmergencyRoomWithDistance> realTimeEmergencyRoomWithDistances =
                new ArrayList<>();
        for (RealTimeEmergencyInfo realTimeEmergencyInfo : realTimeEmergencyInfos) {
            EmergencyRoom emergencyRoom =
                    emergencyRoomReader.read(realTimeEmergencyInfo.hospitalId());

            double distance =
                    calculateDistance(
                            currentCoordinate, emergencyRoom.getLocation().toCoordinate());

            realTimeEmergencyRoomWithDistances.add(
                    RealTimeEmergencyRoomWithDistance.of(realTimeEmergencyInfo, distance));
        }
        return realTimeEmergencyRoomWithDistances;
    }

    public double calculateDistance(Coordinate currentCoordinate, Coordinate targetCoordinate) {
        final int EARTH_RADIUS_KM = 6371; // 지구의 평균 반지름 (킬로미터 단위)

        double lat1 = Math.toRadians(currentCoordinate.latitude());
        double lon1 = Math.toRadians(currentCoordinate.longitude());
        double lat2 = Math.toRadians(targetCoordinate.latitude());
        double lon2 = Math.toRadians(targetCoordinate.longitude());

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a =
                Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                        + Math.cos(lat1)
                                * Math.cos(lat2)
                                * Math.sin(deltaLon / 2)
                                * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
