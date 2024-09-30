package com.nbe2.api.emergencyroom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my")
public class EmergencyRoomApi2 {

    @GetMapping("/test")
    public Response<Void> test() {
        String tt = "test 성공";
        return Response.success(tt);
    }

    @GetMapping("/admin/pendings")
    public Response<Void> test2() {
        String tt = "test2 성공";
        return Response.success(tt);
    }
}
