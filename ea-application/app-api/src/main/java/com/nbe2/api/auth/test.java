package com.nbe2.api.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class test {

    @GetMapping("/notices")
    public void test1() {
        System.out.println("성공입니다잉");
    }

    @GetMapping("/my")
    public void test2() {
        System.out.println("성공입니다잉1");
    }
}
