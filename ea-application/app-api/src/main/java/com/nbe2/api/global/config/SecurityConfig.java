package com.nbe2.api.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("filterChain");
        httpSecurity
                // CSRF 비활성화: JWT를 사용할 경우 CSRF 공격을 방지할 필요가 없음
                .csrf(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화 -> httpBasic은 비밀번호를 text로 전송하는 형식
                // 여기서는 JWT같은 암호화된 방식을 사용하므로 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // Spring Security 기본 Login Form 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // URL 경로에 따른 접근 제어
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        // login 접근은 누구나 가능
                                        .requestMatchers(("/jwt/test/login"))
                                        .permitAll()
                                        .requestMatchers(("/api/v1/oauth/**"))
                                        .permitAll()
                                        .requestMatchers(("/api/v1/auth/**"))
                                        .permitAll()
                                        // 커뮤니티 조회 ( 아직 API 명세가 안된거 같음 )

                                        // 응급실 공지사항 및 댓글 조회
                                        .requestMatchers(HttpMethod.GET, "/api/v1/notices/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1/reviews/")
                                        .permitAll()
                                        // 응급실 맵 관련 조회 ( 지도,길찾기 )
                                        .requestMatchers(HttpMethod.GET, "/api/v1/directions")
                                        .permitAll()
                                        .requestMatchers(
                                                HttpMethod.GET, "/api/v1/emergency-rooms/**")
                                        .permitAll()

                                        // 응급실 공지사항 수정,삭제,등록은 관계자만 가능
                                        .requestMatchers(HttpMethod.POST, "/api/v1/notices")
                                        .hasAnyRole("MEDICAL_PERSON")
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/notices")
                                        .hasAnyRole("MEDICAL_PERSON")
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/notices")
                                        .hasAnyRole("MEDICAL_PERSON")

                                        // 관계자 가입 및 승인은 ADMIN만 가능
                                        .requestMatchers("/api/v1/auth/pendings/**")
                                        .hasAnyRole("ADMIN")

                                        // 다른 모든 요청은 인증이 필요
                                        .anyRequest()
                                        .authenticated())
                // JWT는 무상태(stateless) 특성을 가지므로 세션 관리 비활성화
                // JWT를 사용할 때는 클라이언트가 토큰을 관리하므로 세션이 필요 없음
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
