package com.nbe2.api.global.config;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.util.JwtUtils;

@RequiredArgsConstructor
@Component
public class CustomSecurityFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = reversToken(request);
        if (jwtUtils.validateJwt(jwtToken)) {
            String role = jwtUtils.getRole(jwtToken);
            String userId = jwtUtils.getUserId(jwtToken);
            List<GrantedAuthority> grantedAuthorities = convertorGrantedAuthority(role);
            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                System.out.println(grantedAuthority.getAuthority());
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    // 사용자의 Role을 Spring Context ROLE 정보에 맞게 변환
    private List<GrantedAuthority> convertorGrantedAuthority(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    // 헤더에서 토큰 추출
    private String reversToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            if (bearerToken.length() > 7) {
                return bearerToken.substring(7);
            }
        }
        // 토큰 정보 칸이 비어있으면 없는 토큰으로 간주하고 오류 발생
        return null;
    }
}
