package com.nbe2.domain.notice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Long writeNotice(NoticeInfo noticeInfo, Long emergencyRoomId, Long userId) {
        return noticeRepository.save(noticeInfo, emergencyRoomId, userId);
    }
}
