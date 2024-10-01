package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final CoordinateToRegionConverter coordinateConverter;
    private final RealTimeEmergencyRoomInfoFetcher realTimeInfoFetcher;
    private final DistanceCalculator distanceCalculator;
    private final EmergencyRoomInitializer emergencyRoomInitializer;
    private final EmergencyRoomReader emergencyRoomReader;
    private final EmergencyRoomDirections emergencyRoomDirections;

    @Transactional
    public void init() {
        emergencyRoomInitializer.init();
    }

    public List<RealTimeEmergencyRoomWithDistance> getRealTimeEmergencyRooms(
            Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyRoomInfo> realTimeInfos = realTimeInfoFetcher.fetch(region);
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate);
    }

    public List<String> getEmergencyRoomListForName(String name) {
        return emergencyRoomReader.readByHospitalName(name);
    }

    public void directionsEmergencyRoom(String hospitalName, String myLocation) {
        emergencyRoomDirections.directionsEmergencyRoom(hospitalName, myLocation);
    }
}
