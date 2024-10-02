package com.nbe2.domain.emergencyroom;

import java.util.List;

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

    public List<String> readByHospitalName(String hospitalName) {
        return emergencyRoomRepository.findByHospitalNameContaining(hospitalName).stream()
                .map(EmergencyRoom::getHpId)
                .toList();
    }

    public List<EmergencyRoomMapInfo> read(Coordinate coordinate, double distance) {
        return emergencyRoomRepository.findByCoordinateAndDistance(coordinate, distance);
    }
}
