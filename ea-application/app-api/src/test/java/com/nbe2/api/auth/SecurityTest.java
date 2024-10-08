package com.nbe2.api.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.nbe2.domain.auth.AdminAuthService;
import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.UserRole;
import com.nbe2.security.config.CustomAccessDeniedHandler;
import com.nbe2.security.config.JwtAuthenticationEntryPoint;
import com.nbe2.security.config.SecurityConfig;
import com.nbe2.security.utils.JwtGenerator;
import com.nbe2.security.utils.JwtProvider;

@Import(SecurityConfig.class)
@WebMvcTest(value = AdminAuthApi.class)
public class SecurityTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private AdminAuthService authService;

    @MockBean private JwtProvider jwtProvider;

    @MockBean private JwtGenerator jwtGenerator;

    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean private CustomAccessDeniedHandler customAccessDeniedHandler;

    private Tokens generatedJwtToken(UserRole userRole) {
        return Tokens.builder()
                .accessToken("accessTestToken")
                .refreshToken("refreshTestToken")
                .build();
    }

    @DisplayName("관리자는 모든 URL에 접근이 가능해야 한다.")
    @Test
    public void adminUserTest() throws Exception {
        // given
        Tokens tokens = generatedJwtToken(UserRole.ADMIN);

        when(jwtGenerator.generate(any(UserPrincipal.class))).thenReturn(tokens);

        // when
        mockMvc.perform(
                        get("/api/v1/auth/admin/pendings")
                                .header("Authorization", "Bearer " + tokens.accessToken()))
                .andExpect(status().isOk());

        // then

    }
    //
    //    @Test
    //    public void userTest() {
    //        Tokens tokens = generatedJwtToken(UserRole.USER);
    //    }
    //
    //    @Test
    //    public void medicalPersonTest() {
    //        Tokens tokens = generatedJwtToken(UserRole.MEDICAL_PERSON);
    //    }
    //
    //    @Test
    //    public void guestUserTest() {
    //    }
}
