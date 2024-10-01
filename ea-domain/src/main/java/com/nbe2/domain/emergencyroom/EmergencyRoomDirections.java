package com.nbe2.domain.emergencyroom;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmergencyRoomDirections {

    private final EmergencyRoomClient roomClient;
    private final EmergencyRoomRepository emergencyRoomRepository;

    public void directionsEmergencyRoom(String hospitalName, String myLocation) {

        roomClient.directionsEmergencyRoom(null, null);
    }
}
