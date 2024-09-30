package com.nbe2.domain.notice;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeReader {
    private final NoticeRepository noticeRepository;

    @Transactional
    public List<NoticeReadInfo> readAllById(Long emergencyRoomId) {
        return noticeRepository.findByEmergencyRoomId(emergencyRoomId);
    }
}
