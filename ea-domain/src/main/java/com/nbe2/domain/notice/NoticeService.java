package com.nbe2.domain.notice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.user.User;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeAppender noticeAppender;

    public Notice writeNotice(NoticeInfo noticeInfo, Long userId) { // Notice 등록
        EmergencyRoom noticeAppenderEmergencyRoom = noticeAppender.getEmergencyRoom(noticeInfo);
        User noticeAppenderUser = noticeAppender.getUser(userId);
        return noticeAppender.append(noticeInfo, noticeAppenderUser, noticeAppenderEmergencyRoom);
    }

    public void deleteNotice(Long noticeId) {
        // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 삭제 가능하게 해야 됨
        noticeAppender.deleteNotice(noticeId);
    }

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId) {
        // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 수정 가능하게 해야 됨
        noticeAppender.updateNotice(updateInfo, noticeId);
    }

    public List<NoticeReadInfo> readNotice(Long emergecnyRoomId) {
        return noticeAppender.readAllById(emergecnyRoomId);
    }
}
