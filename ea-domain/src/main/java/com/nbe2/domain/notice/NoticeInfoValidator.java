package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.exception.NoticeNotFoundContentException;
import com.nbe2.domain.notice.exception.NoticeNotFoundTitleExcepion;

@Component
@RequiredArgsConstructor
public class NoticeInfoValidator {

    public void validateNotice(NoticeInfo noticeInfo) {
        noticeInfo.title().orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
        noticeInfo.content().orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }

    public void validateUpdateNotice(NoticeUpdateInfo noticeUpdateInfo) {
        noticeUpdateInfo.title().orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
        noticeUpdateInfo.content().orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }
}
