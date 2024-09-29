package com.nbe2.api.notice.dto;

import com.nbe2.domain.notice.NoticeReadInfo;

public record NoticeReadResponse(Long emergencyRoomId, String title, String content) {
    public static NoticeReadResponse fromNoticeReadInfo(NoticeReadInfo noticeReadInfo) {
        return new NoticeReadResponse(
                noticeReadInfo.emergencyRoomId(), noticeReadInfo.title(), noticeReadInfo.content());
    }
}
