package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeDeleter {
    private final NoticeRepository noticeRepository;
    private final NoticeReader noticeReader;
    private final NoticeValidator noticeValidator;

    public void deleteNotice(Long noticeId, Long userId) {
        Notice notice = noticeReader.readNotice(noticeId);
        noticeValidator.validateAuthor(notice, userId);
        noticeRepository.delete(notice);
    }
}
