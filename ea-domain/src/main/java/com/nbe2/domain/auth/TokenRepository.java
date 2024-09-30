package com.nbe2.domain.auth;

public interface TokenRepository {

    void setRefreshToken(RefreshToken refreshToken);

    void removeRefreshToken(long userId);

    RefreshToken getRefreshToken(long userId);
}
