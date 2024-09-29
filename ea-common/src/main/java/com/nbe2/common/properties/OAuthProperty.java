package com.nbe2.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuthProperty {

    public static String BASE_URL;
    public static String CLIENT_ID;
    public static String REDIRECT_URI;

    public static final String KAKAO_OAUTH_QUERY_STRING =
            "/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s";

    @Value("${oauth.kakao.base-url}")
    public void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    @Value("${oauth.kakao.client-id}")
    public void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }

    @Value("${oauth.kakao.redirect-url}")
    public void setRedirectUri(String redirectUri) {
        REDIRECT_URI = redirectUri;
    }
}
