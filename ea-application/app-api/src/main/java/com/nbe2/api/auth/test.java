package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class test {

    @GetMapping("/my")
    public Response<Void> test() {
        return Response.success();
    }
}
