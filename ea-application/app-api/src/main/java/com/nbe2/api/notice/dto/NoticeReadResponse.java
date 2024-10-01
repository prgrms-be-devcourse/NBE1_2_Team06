package com.nbe2.api.notice.dto;

import java.util.List;

import com.nbe2.domain.notice.NoticeReadInfo;

public record NoticeReadResponse(
        Long emergencyRoomId, String title, String content, List<Long> fileIds) {
    public static NoticeReadResponse fromNoticeReadInfo(NoticeReadInfo noticeReadInfo) {
        return new NoticeReadResponse(
                noticeReadInfo.emergencyRoomId(),
                noticeReadInfo.title(),
                noticeReadInfo.content(),
                noticeReadInfo.fileIds());
    }
}
