package com.nbe2.api.notice.dto;

import java.util.Optional;

import com.nbe2.domain.notice.NoticeInfo;

public record NoticteCreateRequest(String title, String content, String hpId) {
    public NoticeInfo toNoticeInfo() {
        return new NoticeInfo(Optional.ofNullable(title), Optional.ofNullable(content), hpId);
    }
}
