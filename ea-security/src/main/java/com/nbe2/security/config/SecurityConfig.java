package com.nbe2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import com.nbe2.security.constants.SecurityUrlEndPoint;
import com.nbe2.security.utils.JwtProvider;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
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
                        authorizationManagerRequestMatcherRegistry -> {
                            // All
                            for (SecurityUrlEndPoint securityUrlEndPoint :
                                    SecurityUrlEndPoint.values()) {
                                if (securityUrlEndPoint.getUserRole() == null) {
                                    authorizationManagerRequestMatcherRegistry
                                            .requestMatchers(
                                                    securityUrlEndPoint.getMethod(),
                                                    securityUrlEndPoint.getUrl())
                                            .permitAll();
                                }
                            }

                            for (SecurityUrlEndPoint securityUrlEndPoint :
                                    SecurityUrlEndPoint.values()) {
                                if (securityUrlEndPoint.getUserRole() != null) {
                                    authorizationManagerRequestMatcherRegistry
                                            .requestMatchers(
                                                    securityUrlEndPoint.getMethod(),
                                                    securityUrlEndPoint.getUrl())
                                            .hasRole(securityUrlEndPoint.getUserRole().getRole());
                                    System.out.println(
                                            "ROLE :: "
                                                    + securityUrlEndPoint.getUserRole().getRole());
                                }
                            }
                            authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                        });

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

    @Bean(name = "customBcryptPasswordEncoder")
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
