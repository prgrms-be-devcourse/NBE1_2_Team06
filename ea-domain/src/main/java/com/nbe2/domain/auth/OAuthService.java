package com.nbe2.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClient oAuthClient;
    private final TokenManager tokenManager;
    private final TokenGenerator tokenGenerator;
    private final UserReader userReader;

    public String getConnectionUrl() {
        return oAuthClient.getConnectionUrl();
    }

    public Tokens login(String code) {
        OAuthProfile oAuthProfile = oAuthClient.getOAuthProfile(code);
        User user = userReader.read(oAuthProfile.getEmail());
        Tokens tokens =
                tokenGenerator.generateToken(UserPrincipal.of(user.getId(), user.getRole()));
        tokenManager.save(RefreshToken.of(user.getId(), tokens.refreshToken()));

        return tokens;
    }
}
