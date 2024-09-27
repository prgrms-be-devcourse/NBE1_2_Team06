package com.nbe2.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeDomain {
    private Long noticeId;
    private Long userId; // user_id
    private Long emergencyRoomId; // emergencyRoomId
    private NoticeInfo noticeInfo; // title , content
}
