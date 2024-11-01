package com.nbe2.api.notice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.nbe2.domain.notice.NoticeInfo;

public record NoticteCreateRequest(
        @NotBlank(message = "제목을 입력해주세요.") String title,
        @NotBlank(message = "내용을 입력해주세요.") String content,
        @NotNull(message = "병원ID를 입력해주세요.") String hpId) {
    public NoticeInfo toNoticeInfo() {
        return new NoticeInfo(title, content, hpId);
    }
}
