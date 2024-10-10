package com.nbe2.security.token;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.auth.Tokens;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.UserRole;
import com.nbe2.security.exception.JwtExpriedException;
import com.nbe2.security.utils.JwtGenerator;
import com.nbe2.security.utils.JwtProvider;

/**
 * 단위 테스트의 경우 yaml 파일을 읽지 못하기 때문에, 객체 생성 시 필요한 값을 매개변수로 직접 설정해준다. JwtGenerator와 JwtProvider는 생성자를 통해
 * 필요한 값을 설정해준다. JwtProvider는 @InjectMocks 어노테이션을 사용해 주입받는다. JwtGenerator는 만료된 토큰과 정상적인 토큰을 테스트할 때
 * 각각의 쓰임새에 맞게 새로운 객체로 생성하여 사용한다.
 */
@ExtendWith(MockitoExtension.class)
public class JwtProviderTest {

    @InjectMocks private static JwtProvider jwtProvider;

    private static final String TEST_SECRET_KEY =
            "fegwhgogjqgeonri3noi523niefaenpffegwhgogjqgeonri3noi523niefaenpf";

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider(TEST_SECRET_KEY);
    }

    // 정상적인 토큰 생성
    private static JwtGenerator jwtGenerator() {
        return new JwtGenerator(TEST_SECRET_KEY, 1600000000000000L, 1600000000000000L);
    }

    // 만료된 토큰 생성
    private static JwtGenerator expiredJwtGenerator() {
        return new JwtGenerator(TEST_SECRET_KEY, -10L, -10L);
    }

    @Test
    @DisplayName("만료된 액세스 토큰인 경우 예외를 발생시킨다.")
    void getExpiredAccessToken() {
        // given
        Tokens generate1 = expiredJwtGenerator().generate(UserPrincipal.of(1L, UserRole.USER));

        // when
        JwtExpriedException exception =
                assertThrows(
                        JwtExpriedException.class,
                        () -> {
                            jwtProvider.getTokenUserPrincipal(generate1.accessToken());
                        });

        // then
        assertEquals("웹 계층 예외 - 만료된 토큰", exception.getMessage());
    }

    @Test
    @DisplayName("만료된 리프레쉬 토큰인 경우 예외를 발생시킨다.")
    void getExpiredRefreshToken() {
        /// give
        Tokens generate1 = expiredJwtGenerator().generate(UserPrincipal.of(2L, UserRole.USER));

        // when
        JwtExpriedException exception =
                assertThrows(
                        JwtExpriedException.class,
                        () -> {
                            jwtProvider.getTokenUserPrincipal(generate1.refreshToken());
                        });

        // then
        assertEquals("웹 계층 예외 - 만료된 토큰", exception.getMessage());
    }

    @Test
    @DisplayName("토큰이 올바르게 생성된다.")
    void createToken() {
        // given
        UserPrincipal userPrincipal = UserPrincipal.of(3L, UserRole.USER);
        Tokens generate = jwtGenerator().generate(userPrincipal);

        // then & when
        assertNotNull(generate);
    }

    @Test
    @DisplayName("유효한 액세스 토큰인 경우 해당 토큰의 정보를 조회한다.")
    void getTokenInfo() {
        // given
        Long id = 4L;
        UserRole userRole = UserRole.USER;
        UserPrincipal userPrincipal = UserPrincipal.of(id, userRole);
        Tokens generate = jwtGenerator().generate(userPrincipal);

        // when
        UserPrincipal tokenUserPrincipal =
                jwtProvider.getTokenUserPrincipal(generate.accessToken());

        // then
        assertEquals(id, tokenUserPrincipal.userId());
        assertEquals(UserRole.USER, tokenUserPrincipal.role());
    }
}
