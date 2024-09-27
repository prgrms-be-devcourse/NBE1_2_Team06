package com.nbe2.infra.auth.cache;

import static com.nbe2.infra.auth.config.TokenRedisConfig.REFRESH_TOKEN_TTL;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.RefreshToken;
import com.nbe2.domain.auth.TokenRepository;

@Repository
@RequiredArgsConstructor
public class RedisTokenRepository implements TokenRepository {

    private final RedisTemplate<String, RefreshToken> template;

    @Override
    public void setRefreshToken(RefreshToken refreshToken) {
        template.opsForValue().set(getKey(refreshToken.userId()), refreshToken, REFRESH_TOKEN_TTL);
    }

    private String getKey(long userId) {
        return "REFRESH_TOKEN_ER:" + userId;
    }
}
