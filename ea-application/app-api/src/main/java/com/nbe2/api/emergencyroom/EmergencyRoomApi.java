package com.nbe2.api.emergencyroom;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.emergencyroom.dto.RealTimeEmergencyRoomResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.emergencyroom.Coordinate;
import com.nbe2.domain.emergencyroom.EmergencyRoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emergency-rooms")
public class EmergencyRoomApi {

    private final EmergencyRoomService emergencyRoomService;

    @PostConstruct
    public void init() {
        emergencyRoomService.getEmergencyRoomData();
    }

    @GetMapping("/real-time")
    public Response<List<RealTimeEmergencyRoomResponse>> getRealTimeEmergencyRooms(
            Double longitude, Double latitude) {
        List<RealTimeEmergencyRoomResponse> responses =
                emergencyRoomService
                        .getRealTimeEmergencyRooms(Coordinate.of(longitude, latitude))
                        .stream()
                        .map(RealTimeEmergencyRoomResponse::from)
                        .toList();
        return Response.success(responses);
    }

    @GetMapping("/search")
    public Response<?> testController2(@RequestParam("hospitalName") String hospitalName) {
        List<String> emergencyRoomListForName =
                emergencyRoomService.getEmergencyRoomListForName(hospitalName);
        return Response.success(emergencyRoomListForName);
    }
}
