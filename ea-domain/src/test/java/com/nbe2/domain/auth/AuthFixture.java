package com.nbe2.domain.auth;

import static com.nbe2.domain.global.TestConstants.*;

public class AuthFixture {

    public static OAuthProfile createOAuthProfile() {
        return new OAuthProfileFixture();
    }

    private static class OAuthProfileFixture implements OAuthProfile {
        private final String nickname;
        private final String email;

        private OAuthProfileFixture() {
            this.nickname = NAME;
            this.email = EMAIL;
        }

        public String getNickname() {
            return nickname;
        }

        public String getEmail() {
            return email;
        }
    }
}
