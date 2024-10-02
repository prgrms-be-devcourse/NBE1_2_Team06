package com.nbe2.domain.emergencyroom;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmergencyRoomDirections {

    private final EmergencyRoomClient roomClient;

    public EmergencyRoomDirectionsInfo directionsEmergencyRoom(
            String myLocation, String latitudeAndLongitude) {
        return roomClient.directionsEmergencyRoom(myLocation, latitudeAndLongitude);
    }
}
