package com.nbe2.domain.emergencyroom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmergencyDirections {

    private final EmergencyRoomClient roomClient;

    public void directionsEmergencyRoom(String hospitalName) {
        roomClient.directionsEmergencyRoom(hospitalName);
    }
}
