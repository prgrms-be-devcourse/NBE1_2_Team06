package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.exception.NoticeNotFoundNoticeIdException;

@Component
@RequiredArgsConstructor
public class NoticeDeleter {
    private final NoticeRepository noticeRepository;

    // 삭제를 위한 findByNoticeId
    public void deleteNotice(Long noticeId) {
        noticeRepository.delete(
                // DTO 처리를 하는게 좋을까? 괜히 하는건 아닐까?
                noticeRepository
                        .findById(noticeId)
                        .orElseThrow(() -> NoticeNotFoundNoticeIdException.EXCEPTION));
    }
}
