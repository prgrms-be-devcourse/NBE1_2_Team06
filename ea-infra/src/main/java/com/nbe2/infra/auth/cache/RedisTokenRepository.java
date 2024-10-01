package com.nbe2.infra.auth.cache;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.AuthConstants;
import com.nbe2.domain.auth.RefreshToken;
import com.nbe2.domain.auth.TokenRepository;

@Repository
@RequiredArgsConstructor
public class RedisTokenRepository implements TokenRepository {

    private final RedisTemplate<String, RefreshToken> template;

    @Override
    public void setRefreshToken(RefreshToken refreshToken) {
        template.opsForValue()
                .set(getKey(refreshToken.userId()), refreshToken, AuthConstants.REFRESH_TOKEN_TTL);
    }

    @Override
    public void removeRefreshToken(long userId) {
        template.delete(getKey(userId));
    }

    @Override
    public Optional<RefreshToken> getRefreshToken(long userId) {
        return Optional.ofNullable(template.opsForValue().get(getKey(userId)));
    }

    private String getKey(long userId) {
        return "REFRESH_TOKEN_ER:" + userId;
    }
}
