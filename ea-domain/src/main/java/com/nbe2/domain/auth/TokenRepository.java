package com.nbe2.domain.auth;

import java.util.Optional;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    void removeRefreshToken(long userId);

    Optional<RefreshToken> getRefreshToken(long userId);
}
