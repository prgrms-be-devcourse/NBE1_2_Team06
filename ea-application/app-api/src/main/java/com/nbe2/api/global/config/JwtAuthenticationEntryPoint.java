package com.nbe2.api.global.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.nbe2.api.global.exception.JwtUnkownException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        if (isExceptionInSecurityFilter(request)) {
            resolver.resolveException(
                    request, response, null, (Exception) request.getAttribute("exception"));
            return;
        }
        resolver.resolveException(request, response, null, JwtUnkownException.EXCEPTION);
    }

    private boolean isExceptionInSecurityFilter(HttpServletRequest request) {
        return request.getAttribute("exception") != null;
    }
}
