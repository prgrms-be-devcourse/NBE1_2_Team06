package com.nbe2.domain.notice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.user.User;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeAppender noticeAppender;
    private final NoticeReader noticeReader;
    private final NoticeUpdater noticeUpdater;
    private final NoticeDeleter noticeDeleter;

    public Notice writeNotice(NoticeInfo noticeInfo, Long userId) { // Notice 등록
        EmergencyRoom noticeAppenderEmergencyRoom = noticeAppender.getEmergencyRoom(noticeInfo);
        User noticeAppenderUser = noticeAppender.getUser(userId);
        return noticeAppender.append(noticeInfo, noticeAppenderUser, noticeAppenderEmergencyRoom);
    }

    public void deleteNotice(Long noticeId) {
        // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 삭제 가능하게 해야 됨
        noticeDeleter.deleteNotice(noticeId);
    }

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId) {
        // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 수정 가능하게 해야 됨
        noticeUpdater.updateNotice(updateInfo, noticeId);
    }

    public PageResult<NoticeReadInfo> readNotice(Long emergecnyRoomId, Page page) {
        return noticeReader.readAllByIdPage(emergecnyRoomId, PagingUtil.toPageRequest(page));
    }
}
