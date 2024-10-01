package com.nbe2.api.user;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.user.dto.MedicalRequest;
import com.nbe2.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PatchMapping("/medical")
    public Response<Void> requestMedicalAuthority(
            @RequestParam Long userId, @RequestBody MedicalRequest medicalRequest) {
        userService.requestMedicalAuthority(userId, medicalRequest.toMedicalProfile());
        return Response.success();
    }
}
