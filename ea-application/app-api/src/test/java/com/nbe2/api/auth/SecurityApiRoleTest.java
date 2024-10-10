package com.nbe2.api.auth;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nbe2.domain.auth.AdminAuthService;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.UserRole;
import com.nbe2.security.config.CustomAccessDeniedHandler;
import com.nbe2.security.config.JwtAuthenticationEntryPoint;
import com.nbe2.security.config.SecurityConfig;
import com.nbe2.security.utils.JwtProvider;

@WebMvcTest(value = AdminAuthApi.class)

//        includeFilters = @ComponentScan.Filter(classes = {CustomSecurityFilter.class})
@Import({SecurityConfig.class})
public class SecurityApiRoleTest {

    @MockBean private AdminAuthService authService;

    @MockBean private JwtProvider jwtProvider;
    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired private MockMvc mockMvc;

    private String validToken;

    @BeforeEach
    public void set() {
        // 유효한 JWT 토큰 생성 (Mock으로 처리)
        validToken = "valid.jwt.token";
        UserPrincipal userPrincipal = UserPrincipal.of(1L, UserRole.USER);
        when(jwtProvider.getTokenUserPrincipal(validToken)).thenReturn(userPrincipal);
    }

    @DisplayName("일반 유저는 특정 URL에 접근이 불가해야 한다.")
    @Test
    @WithMockUser(roles = "USER")
    public void adminUserTest() throws Exception {
        mockMvc.perform(
                        get("/api/v1/auth/admin/pendings")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + validToken))
                //                .andExpect(status().is4xxClientError());
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
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
