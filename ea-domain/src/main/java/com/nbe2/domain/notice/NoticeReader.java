package com.nbe2.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.PageResult;

@Component
@RequiredArgsConstructor
public class NoticeReader {
    private final NoticeRepository noticeRepository;

    @Transactional
    public PageResult<NoticeReadInfo> readAllByIdPage(Long emergencyRoomId, Pageable pageable) {
        Page<NoticeReadInfo> noticeInfos =
                noticeRepository.findByEmergencyRoomIdPage(pageable, emergencyRoomId);
        return new PageResult<>(
                noticeInfos.getContent(), noticeInfos.getTotalPages(), noticeInfos.hasNext());
    }
}
