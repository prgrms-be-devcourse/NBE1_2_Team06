package com.nbe2.domain.emergencyroom;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmergencyRoomInitializer {

    private final EmergencyRoomClient emergencyRoomClient;
    private final EmergencyRoomRepository emergencyRoomRepository;

    public void init() {
        emergencyRoomRepository.saveAll(
                emergencyRoomClient.getEmergencyRoomInfoData().stream()
                        .map(EmergencyRoomInfo::toEmergencyRoom)
                        .toList());
    }
}
