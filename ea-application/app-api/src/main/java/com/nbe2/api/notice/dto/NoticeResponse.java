package com.nbe2.api.notice.dto;

import com.nbe2.domain.notice.NoticeInfo;

public record NoticeResponse(String title, String content) {
    public static NoticeResponse from(NoticeInfo noticeInfo) {
        return new NoticeResponse(noticeInfo.title(), noticeInfo.content());
    }
}
