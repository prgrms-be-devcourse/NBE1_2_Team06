package com.nbe2.domain.emergencyroom;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.repository.EmergencyRoomRepository;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    

    private final EmergencyRoomClient emergencyRoomClient;
    private final EmergencyRoomRepository emergencyRoomRepository;
    private final CoordinateToRegionConverter coordinateConverter;
    private final RealTimeEmergencyRoomInfoFetcher realTimeInfoFetcher;
    private final DistanceCalculator distanceCalculator;

    public List<RealTimeEmergencyRoomWithDistance> getRealTimeEmergencyRooms(
            Coordinate currentCoordinate) {
        Region region = coordinateConverter.convert(currentCoordinate);
        List<RealTimeEmergencyRoomInfo> realTimeInfos = realTimeInfoFetcher.fetch(region);
        return distanceCalculator.calculate(realTimeInfos, currentCoordinate);
    }

    // 서버 시작시 한번만 동작
    @PostConstruct
    public void init() {
        emergencyRoomRepository.saveAll(
                emergencyRoomClient.getEmergencyRoomInfoData().stream()
                        .map(EmergencyRoomInfo::toEmergencyRoom)
                        .toList());
    }

    public List<RealTimeEmergencyInfo> getRealTimeEmergencyData(String region, String subRegion) {
        return emergencyRoomClient.getRealTimeEmergencyData(region, subRegion);
    }

    public List<String> getEmergencyRoomListForName(String name) {
        return emergencyRoomRepository.findByHospitalNameContaining(name).stream()
                .map(EmergencyRoom::getHospitalName)
                .toList();
    }
}
