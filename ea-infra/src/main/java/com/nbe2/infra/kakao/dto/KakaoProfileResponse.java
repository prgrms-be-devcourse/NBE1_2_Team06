package com.nbe2.infra.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.auth.OAuthProfile;

@Getter
@NoArgsConstructor
public class KakaoProfileResponse implements OAuthProfile {

    private String name;
    private String email;
}
