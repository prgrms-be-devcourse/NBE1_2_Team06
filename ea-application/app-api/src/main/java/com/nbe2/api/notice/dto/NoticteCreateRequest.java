package com.nbe2.api.notice.dto;

import com.nbe2.domain.notice.NoticeInfo;

public record NoticteCreateRequest(String title, String content, String hpId) {
    public NoticeInfo toNoticeInfo() {
        return new NoticeInfo(title, content, hpId);
    }
}
