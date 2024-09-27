package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final RealTimeEmergencyRoomInfoFetcher realTimeInfoFetcher;
    private final CoordinateToRegionConverter coordinateConverter;
    private final DistanceCalculator distanceCalculator;

    public List<RealTimeEmergencyRoomWithDistance> getRealTimeEmergencyRooms(
            Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyRoomInfo> realTimeInfos = realTimeInfoFetcher.fetch(region);
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate);
    }
}
