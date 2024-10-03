package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeDeleter {
    private final NoticeRepository noticeRepository;
    private final NoticeReader noticeReader;

    public void deleteNotice(Long noticeId) {
        noticeRepository.delete(noticeReader.readNotice(noticeId));
    }
}
