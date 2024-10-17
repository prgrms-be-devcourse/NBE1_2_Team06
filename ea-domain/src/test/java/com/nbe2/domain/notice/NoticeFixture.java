package com.nbe2.domain.notice;

import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;

public class NoticeFixture {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    public static Notice create() {
        return Notice.builder()
                .title(TITLE)
                .content(CONTENT)
                .emergencyRoom(EmergencyRoomFixture.createWithId())
                .build();
    }
}
