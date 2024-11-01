package com.nbe2.domain.emergencyroom;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DistanceCalculator {

    private final EmergencyRoomReader emergencyRoomReader;

    public List<RealTimeEmergencyRoomWithDistance> calculate(
            List<RealTimeEmergencyRoomInfo> realTimeEmergencyRoomInfos,
            Coordinate currentCoordinate) {

        return realTimeEmergencyRoomInfos.stream()
                .map(info -> calculateDistance(currentCoordinate, info))
                .sorted(Comparator.comparing(RealTimeEmergencyRoomWithDistance::distance))
                .toList();
    }

    public RealTimeEmergencyRoomWithDistance calculateDistance(
            Coordinate currentCoordinate, RealTimeEmergencyRoomInfo info) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(info.hospitalId());
        double distance = currentCoordinate.distanceTo(emergencyRoom.getLocation());
        return RealTimeEmergencyRoomWithDistance.of(info, distance);
    }
}
