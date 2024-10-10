package com.nbe2.security.config;

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

import com.nbe2.common.constants.EAConstants;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.security.exception.JwtNotFountException;
import com.nbe2.security.utils.JwtProvider;

@RequiredArgsConstructor
@Component
public class CustomSecurityFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = reversToken(request);
            // AccessToken(JWT) 유효한지 검사
            // 유효하지 않으면 Refresh Token을 이용해 새 AccessToken 발급
            UserPrincipal tokenUserPrincipal = jwtProvider.getTokenUserPrincipal(jwtToken);
            List<GrantedAuthority> grantedAuthorities =
                    convertorGrantedAuthority(tokenUserPrincipal.role().getRole());
            setSecurityContextHolder(tokenUserPrincipal, grantedAuthorities);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContextHolder(
            UserPrincipal userPrincipal, List<GrantedAuthority> grantedAuthorities) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userPrincipal, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    // 사용자의 Role을 Spring Context ROLE 정보에 맞게 변환
    private List<GrantedAuthority> convertorGrantedAuthority(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    // 헤더에서 토큰 추출
    private String reversToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(EAConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(EAConstants.BEARER)) {
            if (bearerToken.length() > EAConstants.BEARER.length()) {
                return bearerToken.substring(EAConstants.BEARER.length());
            }
        }
        // 토큰 정보 칸이 비어있으면 없는 토큰으로 간주하고 오류 발생
        throw JwtNotFountException.EXCEPTION;
    }
}
