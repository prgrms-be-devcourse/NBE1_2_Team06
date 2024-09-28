package com.nbe2.infra.auth.client;

import static com.nbe2.common.constants.EAConstants.BEARER;
import static com.nbe2.common.properties.OAuthProperty.CLIENT_ID;
import static com.nbe2.common.properties.OAuthProperty.REDIRECT_URI;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.OAuthClient;
import com.nbe2.domain.auth.OAuthProfile;
import com.nbe2.infra.kakao.client.KakaoAuthClient;
import com.nbe2.infra.kakao.client.KakaoProfileClient;
import com.nbe2.infra.kakao.dto.KakaoTokenResponse;

@Component
@RequiredArgsConstructor
public class KakaoClient implements OAuthClient {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoProfileClient kakaoProfileClient;

    @Override
    public OAuthProfile getOAuthProfile(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoAuthClient.auth(CLIENT_ID, REDIRECT_URI, code);
        return kakaoProfileClient.getUserInfo(BEARER + kakaoTokenResponse.access_token());
    }
}
