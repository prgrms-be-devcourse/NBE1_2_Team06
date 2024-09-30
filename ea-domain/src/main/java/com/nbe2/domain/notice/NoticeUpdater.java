package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.exception.NoticeNotFoundNoticeIdException;

@Component
@RequiredArgsConstructor
public class NoticeUpdater {

    private final NoticeInfoValidator noticeInfoValidator;
    private final NoticeRepository noticeRepository;

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId) {
        noticeInfoValidator.validateUpdateTitle(updateInfo); // title Null 검사
        noticeInfoValidator.validateUpdateContent(updateInfo); // content Null 검사
        Notice before =
                noticeRepository
                        .findById(noticeId)
                        .orElseThrow(() -> NoticeNotFoundNoticeIdException.EXCEPTION);
        before.updateTitle(String.valueOf(updateInfo.title()));
        before.updateContent(String.valueOf(updateInfo.content()));
        noticeRepository.save(before);
    }
}
