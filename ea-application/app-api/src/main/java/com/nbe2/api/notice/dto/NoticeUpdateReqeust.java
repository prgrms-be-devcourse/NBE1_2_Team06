package com.nbe2.api.notice.dto;

import java.util.Optional;

import com.nbe2.domain.notice.NoticeUpdateInfo;

public record NoticeUpdateReqeust(String title, String content) {
    public NoticeUpdateInfo toNoticeUpdateInfo() {
        return new NoticeUpdateInfo(Optional.ofNullable(title), Optional.ofNullable(content));
    }
}
