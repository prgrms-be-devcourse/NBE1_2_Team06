package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.emergencyroom.EmergencyRoomRepository;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NoticeAppender {

    private final EmergencyRoomRepository emergencyRoomRepository;
    private final UserReader userReader;
    private final NoticeRepository noticeRepository;
    private final NoticeInfoValidator noticeInfoValidator;
    private final NoticeFileRepository noticeFileRepository;
    private final EmergencyRoomReader emergencyRoomReader;

    public Notice append(Notice newNotice) { // insert
        System.out.println("content : " + newNotice.getContent());
        return noticeRepository.save(newNotice);
    }

    public Notice createNotice(NoticeInfo newNoticeInfo, Long userId) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(newNoticeInfo.hpId());
        User user = userReader.read(userId);
        noticeInfoValidator.validateNotice(newNoticeInfo);
        return Notice.from(newNoticeInfo, user, emergencyRoom);
    }
}
