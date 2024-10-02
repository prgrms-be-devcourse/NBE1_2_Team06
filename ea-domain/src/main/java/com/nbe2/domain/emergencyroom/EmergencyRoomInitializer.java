package com.nbe2.domain.emergencyroom;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmergencyRoomInitializer {

    private final EmergencyRoomClient emergencyRoomClient;
    private final EmergencyRoomRepository emergencyRoomRepository;

    //    public void init() {
    //        emergencyRoomRepository.saveAll(
    //                emergencyRoomClient.getEmergencyRoomInfoData().stream()
    //                        .map(EmergencyRoomInfo::toEmergencyRoom)
    //                        .toList());
    //    }
    public void init() {
        Set<String> uniqueKeySet = getUniqueKeySet();
        List<EmergencyRoom> emergencyRooms = removeDuplicatesEmergencyData(uniqueKeySet);
        if (!emergencyRooms.isEmpty()) emergencyRoomRepository.saveAll(emergencyRooms);
    }

    private Set<String> getUniqueKeySet() {
        return emergencyRoomRepository.findAll().stream()
                .map(
                        rooms ->
                                rooms.getHospitalName()
                                        + rooms.getLocation().getLongitude()
                                        + rooms.getLocation().getLatitude())
                .collect(Collectors.toSet());
    }

    private List<EmergencyRoom> removeDuplicatesEmergencyData(Set<String> uniqueKeySet) {
        return emergencyRoomClient.getEmergencyRoomInfoData().stream()
                .map(EmergencyRoomInfo::toEmergencyRoom)
                .filter(
                        room -> {
                            String s =
                                    room.getHospitalName()
                                            + room.getLocation().getLongitude()
                                            + room.getLocation().getLatitude();
                            boolean contains = uniqueKeySet.contains(s);
                            return !contains;
                        })
                .toList();
    }
}
