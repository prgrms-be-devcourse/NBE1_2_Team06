package com.nbe2.domain.emergencyroom;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.repository.EmergencyRoomRepository;

@Service
@RequiredArgsConstructor
public class EmergencyRoomService {

    private final EmergencyRoomClient emergencyRoomClient;
    private final EmergencyRoomRepository emergencyRoomRepository;

    public List<RealTimeEmergencyInfo> getRealTimeEmergencyData(String region, String subRegion) {
        return emergencyRoomClient.getRealTimeEmergencyData(region, subRegion);
    }

    // 서버 시작시 한번만 동작
    public void getEmergencyRoomData() {
        emergencyRoomRepository.saveAll(
                emergencyRoomClient.getEmergencyRoomInfoData().stream()
                        .map(EmergencyRoomInfo::toEmergencyRoom)
                        .toList());
    }

    public List<String> getEmergencyRoomListForName(String name) {
        return emergencyRoomRepository.findByHospitalNameContaining(name).get().stream()
                .map(EmergencyRoom::getHospitalName)
                .toList();
    }
}
