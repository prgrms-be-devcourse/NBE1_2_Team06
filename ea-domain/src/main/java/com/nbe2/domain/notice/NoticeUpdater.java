package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeUpdater {

    private final NoticeInfoValidator noticeInfoValidator;
    private final NoticeRepository noticeRepository;
    private final NoticeReader noticeReader;

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId, Long userId) {
        noticeInfoValidator.validateNull(updateInfo.title(), updateInfo.content());
        Notice before = noticeReader.readNotice(noticeId);

        noticeInfoValidator.validateUserId(before, userId);
        before.updateNotice(updateInfo.title().get(), updateInfo.content().get());
        noticeRepository.save(before);
    }
}
