package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomRepository;
import com.nbe2.domain.notice.exception.NoticeNotFoundHpIdException;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NoticeAppender {

    private final EmergencyRoomRepository emergencyRoomRepository;
    private final UserReader userReader;
    private final NoticeRepository noticeRepository;

    public void append(NoticeInfo noticeInfo, User user, EmergencyRoom emergencyRoom) {
        noticeRepository.save(Notice.from(noticeInfo, user, emergencyRoom));
    }

    // noticeInfo dto에 있는 hpId로 emergencyRomm 조회
    public EmergencyRoom getEmergencyRoom(NoticeInfo noticeInfo) {
        return emergencyRoomRepository
                .findByHpId(noticeInfo.hpId())
                .orElseThrow(() -> NoticeNotFoundHpIdException.EXCEPTION);
    }

    public User getUser(Long userId) {
        return userReader.read(userId);
    }
}
