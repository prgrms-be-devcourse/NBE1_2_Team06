package com.nbe2.domain.emergencyroom;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.exception.EmergencyRoomNotFoundException;

@Component
@RequiredArgsConstructor
public class EmergencyRoomReader {

    private final EmergencyRoomRepository emergencyRoomRepository;

    public EmergencyRoom read(String hospitalId) {
        return emergencyRoomRepository
                .findByHpId(hospitalId)
                .orElseThrow(() -> EmergencyRoomNotFoundException.EXCEPTION);
    }
}
