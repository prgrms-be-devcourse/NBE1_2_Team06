package com.nbe2.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.notice.exception.NoticeNotFoundNoticeIdException;

@Component
@RequiredArgsConstructor
public class NoticeReader {
    private final NoticeRepository noticeRepository;

    public PageResult<NoticeReadInfo> readAll(Pageable pageable, Long emergencyRoomId) {
        Page<NoticeReadInfo> readNotices =
                noticeRepository
                        .findByEmergencyRoomId(pageable, emergencyRoomId)
                        .map(NoticeReadInfo::convertToNoticeReadInfo);
        return new PageResult<>(
                readNotices.getContent(), readNotices.getTotalPages(), readNotices.hasNext());
    }

    public PageResult<NoticeReadInfo> searchTitle(
            Pageable pageable, Long emergencyRoomId, String title) {
        Page<NoticeReadInfo> searchTitles =
                noticeRepository
                        .findByEmergencyRoomIdAndTitle(pageable, emergencyRoomId, title)
                        .map(NoticeReadInfo::convertToNoticeReadInfo);
        return new PageResult<>(
                searchTitles.getContent(), searchTitles.getTotalPages(), searchTitles.hasNext());
    }

    public Notice findByNoticeId(Long noticeId) {
        return noticeRepository
                .findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundNoticeIdException.EXCEPTION);
    }
}
