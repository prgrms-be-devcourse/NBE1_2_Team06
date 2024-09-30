package com.nbe2.api.global.config;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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

import com.nbe2.api.global.jwt.JwtGenerator;
import com.nbe2.api.global.util.JwtUtils;
import com.nbe2.domain.auth.*;

@RequiredArgsConstructor
@Component
public class CustomSecurityFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtUtils jwtUtils;
    private final JwtGenerator jwtGenerator;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = reversToken(request);

        // AccessToken(JWT) 유효한지 검사
        // 유효하지 않으면 Refresh Token을 이용해 새 AccessToken 발급
        if (jwtToken != null && jwtUtils.validateJwt(jwtToken)) {
            System.out.println("전부다 유효합니다.");
            String role = jwtUtils.getRole(jwtToken);
            String userId = jwtUtils.getUserId(jwtToken);
            List<GrantedAuthority> grantedAuthorities = convertorGrantedAuthority(role);
            setSecurityContextHolder(userId, grantedAuthorities);
            //        }else{
            //            String refreshToken = getRefreshTokenFromCookie(request);
            //            if (refreshToken != null && jwtUtils.validateJwt(refreshToken)) {
            //                System.out.println("리프레쉬 토큰만 유효");
            //                //리프레쉬 토큰만 유효한 경우 클라이언트로 refresh로 다시 요청하라고 메세지를 전달
            //                //그럼 API 에서 refresh로 요청이 들어오면 요청을 받고
            //                //리프레쉬 토큰을 사용해서 액세스 토큰을 재발급.
            //
            //            } else {
            //                System.out.println("둘다 유효하지 않음");
            //                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "다시 로그인
            // 해주세요");
            //                return;
            //            }
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContextHolder(
            String userId, List<GrantedAuthority> grantedAuthorities) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String getRefreshTokenFromCookie(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (AuthConstants.REFRESH_TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
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
