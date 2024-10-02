package com.nbe2.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserAppender;
import com.nbe2.domain.user.UserReader;
import com.nbe2.domain.user.UserValidator;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final OAuthClient oAuthClient;
    private final TokenManager tokenManager;
    private final TokenGenerator tokenGenerator;
    private final UserValidator userValidator;
    private final UserReader userReader;
    private final UserAppender userAppender;

    public String getConnectionUrl() {
        return oAuthClient.getConnectionUrl();
    }

    public Tokens login(String code) {
        OAuthProfile oAuthProfile = oAuthClient.getOAuthProfile(code);
        if (!userValidator.isEmailExists(oAuthProfile.getEmail())) {
            userAppender.append(oAuthProfile);
        }

        User user = userReader.read(oAuthProfile.getEmail());
        Tokens tokens = tokenGenerator.generate(UserPrincipal.of(user.getId(), user.getRole()));
        tokenManager.save(RefreshToken.of(user.getId(), tokens.refreshToken()));

        return tokens;
    }
}
