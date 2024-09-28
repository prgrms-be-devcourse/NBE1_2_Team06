package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.exception.NoticeNotFoundContentException;
import com.nbe2.domain.notice.exception.NoticeNotFoundTitleExcepion;

@Component
@RequiredArgsConstructor
public class NoticeInfoValidator {

    public void validateTitle(NoticeInfo noticeInfo) {
        noticeInfo.title().orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
    }

    public void validateContent(NoticeInfo noticeInfo) {
        noticeInfo.content().orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }
}
