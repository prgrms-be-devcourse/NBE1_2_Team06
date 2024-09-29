package com.nbe2.domain.auth;

import static com.nbe2.common.properties.OAuthProperty.BASE_URL;
import static com.nbe2.common.properties.OAuthProperty.CLIENT_ID;
import static com.nbe2.common.properties.OAuthProperty.KAKAO_OAUTH_QUERY_STRING;
import static com.nbe2.common.properties.OAuthProperty.REDIRECT_URI;

public interface OAuthClient {

    default String getConnectionUrl() {
        return BASE_URL + String.format(KAKAO_OAUTH_QUERY_STRING, CLIENT_ID, REDIRECT_URI);
    }

    OAuthProfile getOAuthProfile(String code);
}
