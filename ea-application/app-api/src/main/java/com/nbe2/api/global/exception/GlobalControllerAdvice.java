package com.nbe2.api.global.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.nbe2.api.global.dto.Response;
import com.nbe2.common.exception.CustomException;
import com.nbe2.common.exception.ErrorReason;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> customError(CustomException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(
                        Response.error(
                                e.getErrorCode().getErrorReason(),
                                request.getRequestURI(),
                                e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> error(Exception e, HttpServletRequest request) {
        log.error("error", e);
        return ResponseEntity.status(500)
                .body(
                        Response.error(
                                ErrorReason.of(500, "INTERNAL_SERVER_ERROR", "알수없는 서버 에러"),
                                request.getRequestURI(),
                                e.getMessage()));
    }
}
