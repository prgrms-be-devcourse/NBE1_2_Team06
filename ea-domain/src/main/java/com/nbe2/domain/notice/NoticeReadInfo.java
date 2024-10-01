package com.nbe2.domain.notice;

import java.util.List;

public record NoticeReadInfo(
        Long emergencyRoomId, Long userId, String title, String content, List<Long> fileIds) {

    public static NoticeReadInfo convertToNoticeReadInfo(Notice notice) {
        return new NoticeReadInfo(
                notice.getEmergencyRoom().getId(),
                notice.getUser().getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getFileIds());
    }
}
