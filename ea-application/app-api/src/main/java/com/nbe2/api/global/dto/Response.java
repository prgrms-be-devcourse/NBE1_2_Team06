package com.nbe2.api.global.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nbe2.common.exception.ErrorReason;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Response<T> {

    private String path;

    private String responseCode;
    private String message;

    private T result;

    private LocalDateTime timeStamp;

    private Response(String responseCode, T result) {
        this.responseCode = responseCode;
        this.result = result;
    }

    private Response(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public Response(String path, String errorCode, String message) {
        this.path = path;
        this.responseCode = errorCode;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    }

    public static Response<Void> success() {
        return new Response<>("SUCCESS", null);
    }

    public static Response<Void> success(String message) {
        return new Response<>("SUCCESS", message);
    }

    public static Response<Void> error(ErrorReason errorReason, String path, String message) {
        return new Response<>(path, errorReason.errorCode(), message);
    }

    public static Response<Void> error(String errorCode, String message) {
        return new Response<>(errorCode, message);
    }
}
