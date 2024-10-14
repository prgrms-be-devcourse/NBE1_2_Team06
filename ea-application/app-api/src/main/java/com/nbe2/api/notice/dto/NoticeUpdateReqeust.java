package com.nbe2.api.notice.dto;

import jakarta.validation.constraints.NotBlank;

import com.nbe2.domain.notice.NoticeUpdateInfo;

public record NoticeUpdateReqeust(
        @NotBlank(message = "수정할 제목을 입력해주세요.") String title,
        @NotBlank(message = "수정할 내용을 입력해주세요.") String content) {
    public NoticeUpdateInfo toNoticeUpdateInfo() {
        return new NoticeUpdateInfo(title, content);
    }
}
