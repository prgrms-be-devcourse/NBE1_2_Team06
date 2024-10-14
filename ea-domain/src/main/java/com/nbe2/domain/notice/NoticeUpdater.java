package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeUpdater {

    private final NoticeValidator noticeValidator;
    private final NoticeRepository noticeRepository;
    private final NoticeReader noticeReader;

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId, Long userId) {
        Notice before = noticeReader.readNotice(noticeId);

        noticeValidator.validateAuthor(before, userId);
        before.updateNotice(updateInfo.title(), updateInfo.content());
        noticeRepository.save(before);
    }
}
