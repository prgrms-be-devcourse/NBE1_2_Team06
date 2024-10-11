package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeDeleter {
    private final NoticeRepository noticeRepository;
    private final NoticeReader noticeReader;
    private final NoticeInfoValidator noticeInfoValidator;

    public void deleteNotice(Long noticeId, Long userId) {
        Notice notice = noticeReader.readNotice(noticeId);
        noticeInfoValidator.validateUserId(notice, userId);
        noticeRepository.delete(notice);
    }
}
