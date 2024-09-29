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

    // 이렇게 새로 만드는게 나을까요 위의 메소드를 재사용 가능하게 하는게 나을까요?
    public void validateUpdateTitle(NoticeUpdateInfo noticeUpdateInfo) {
        noticeUpdateInfo.title().orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
    }

    public void validateUpdateContent(NoticeUpdateInfo noticeUpdateInfo) {
        noticeUpdateInfo.content().orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }
}
