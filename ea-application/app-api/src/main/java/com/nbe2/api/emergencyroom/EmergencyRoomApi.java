package com.nbe2.api.emergencyroom;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.emergencyroom.dto.EmergencyRoomDirectionsResponse;
import com.nbe2.api.emergencyroom.dto.EmergencyRoomMapResponse;
import com.nbe2.api.emergencyroom.dto.RealTimeEmergencyRoomResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.emergencyroom.Coordinate;
import com.nbe2.domain.emergencyroom.EmergencyRoomDetailInfo;
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo;
import com.nbe2.domain.emergencyroom.EmergencyRoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emergency-rooms")
public class EmergencyRoomApi {

    private final EmergencyRoomService emergencyRoomService;

    @GetMapping("/init")
    public Response<Void> init() {
        emergencyRoomService.init();
        return Response.success("전국 응급실 데이터 저장 완료");
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
    public Response<List<String>> saveSearEmergency(@RequestParam String hospitalName) {
        List<String> emergencyRoomListForName =
                emergencyRoomService.getEmergencyRoomListForName(hospitalName);
        return Response.success(emergencyRoomListForName);
    }

    @GetMapping("/directions")
    public Response<EmergencyRoomDirectionsResponse> directionsEmergency(
            @RequestParam("myLocation") String myLocation,
            @RequestParam("hospitalName") String hospitalName) {
        EmergencyRoomDirectionsInfo emergencyRoomDirectionsInfo =
                emergencyRoomService.directionsEmergencyRoom(myLocation, hospitalName);
        EmergencyRoomDirectionsResponse emergencyRoomDirectionsResponse =
                EmergencyRoomDirectionsResponse.to(emergencyRoomDirectionsInfo);
        return Response.success(emergencyRoomDirectionsResponse);
    }

    @GetMapping("/map")
    public Response<List<EmergencyRoomMapResponse>> getEmergencyRooms(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam Double distance) {
        List<EmergencyRoomMapResponse> responses =
                emergencyRoomService
                        .getEmergencyRooms(Coordinate.of(longitude, latitude), distance)
                        .stream()
                        .map(EmergencyRoomMapResponse::from)
                        .toList();
        return Response.success(responses);
    }

    @GetMapping("/{emergencyRoomId}")
    public Response<EmergencyRoomDetailInfo> getEmergencyRoomDetail(
            @PathVariable Long emergencyRoomId) {
        EmergencyRoomDetailInfo emergencyRoomDetail =
                emergencyRoomService.getEmergencyRoomDetail(emergencyRoomId);
        return Response.success(emergencyRoomDetail);
    }
}
