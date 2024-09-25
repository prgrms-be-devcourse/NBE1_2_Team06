package com.nbe2.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.jwt.JwtUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class test {

    @PostMapping("test")
    public void test() {
        String dd1 = JwtUtils.createToken("dd");
        System.out.println(dd1);
        String dd = JwtUtils.getUserId(dd1);
        System.out.println(dd);
        System.out.println(JwtUtils.validateToken(dd1));
    }
}
