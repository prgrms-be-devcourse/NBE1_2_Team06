package com.nbe2.domain.auth;

import org.springframework.stereotype.Component;

@Component
public class TempTokenGenerator implements TokenGenerator {

    @Override
    public Tokens generateToken(UserPrincipal principal) {
        return new Tokens("access", "refresh");
    }
}
