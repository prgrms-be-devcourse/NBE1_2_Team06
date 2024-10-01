package com.nbe2.api.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.jwt.JwtProvider;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("filterChain");
        httpSecurity
                // CSRF 비활성화: JWT를 사용할 경우 CSRF 공격을 방지할 필요가 없음
                .csrf(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // 세션 관리 설정: Stateless
                .sessionManagement(
                        sessionManagement ->
                                sessionManagement.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))
                // 필터 추가
                .addFilterBefore(
                        new CustomSecurityFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class)
                // 접근 제어 설정
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        // 비회원 접근 허용
                                        // 가입 관련
                                        .requestMatchers(HttpMethod.POST, "/api/v1/oauth/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**")
                                        .permitAll()
                                        // 응급실 관련
                                        .requestMatchers(HttpMethod.GET, "/api/v1/notices/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/api/v1/directions")
                                        .permitAll()
                                        .requestMatchers(
                                                HttpMethod.GET, "/api/v1/emergency-rooms/**")
                                        .permitAll()
                                        // 커뮤 관련
                                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**")
                                        .permitAll()
                                        // 의료 관계자 접근 설정
                                        .requestMatchers(HttpMethod.POST, "/api/v1/notices")
                                        .hasRole("MEDICAL_PERSON")
                                        .requestMatchers(HttpMethod.PUT, "/api/v1/notices")
                                        .hasRole("MEDICAL_PERSON")
                                        .requestMatchers(HttpMethod.DELETE, "/api/v1/notices")
                                        .hasRole("MEDICAL_PERSON")
                                        // 관리자 접근 설정
                                        .requestMatchers("/api/v1/admin/pendings")
                                        .hasRole("ADMIN")
                                        // 그 외 회원 인증 필요
                                        .anyRequest()
                                        .authenticated());

        httpSecurity.exceptionHandling(
                httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                // 토큰
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler));

        return httpSecurity.build();
    }

    // 특정 URI 필터 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .requestMatchers("/api/v1/oauth/**")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**")
                        .requestMatchers(HttpMethod.GET, "/api/v1/notices/**")
                        .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**")
                        .requestMatchers(HttpMethod.GET, "/api/v1/directions")
                        .requestMatchers(HttpMethod.GET, "/api/v1/emergency-rooms/**")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/reissue")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/admin/reissue");
    }
}
