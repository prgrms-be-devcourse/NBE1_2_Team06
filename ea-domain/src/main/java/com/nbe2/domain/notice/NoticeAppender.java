package com.nbe2.domain.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomRepository;
import com.nbe2.domain.notice.exception.NoticeNotFoundHpIdException;
import com.nbe2.domain.notice.exception.NoticeNotFoundNoticeIdException;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NoticeAppender {

    private final EmergencyRoomRepository emergencyRoomRepository;
    private final UserReader userReader;
    private final NoticeRepository noticeRepository;
    private final NoticeInfoValidator noticeInfoValidator;

    public Notice append(NoticeInfo noticeInfo, User user, EmergencyRoom emergencyRoom) { // insert
        noticeInfoValidator.validateTitle(noticeInfo); // title Null 검사
        noticeInfoValidator.validateContent(noticeInfo); // content Null 검사
        return noticeRepository.save(Notice.from(noticeInfo, user, emergencyRoom));
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

    // 삭제를 위한 findByNoticeId
    public void deleteNotice(Long noticeId) {
        noticeRepository.delete(
                // DTO 처리를 하는게 좋을까? 괜히 하는건 아닐까?
                noticeRepository
                        .findById(noticeId)
                        .orElseThrow(() -> NoticeNotFoundNoticeIdException.EXCEPTION));
    }

    @Transactional
    public Long getEmergencyRoomId(String hpId) {
        /*Notices 에는 hpId로 저장되어 있지 않고 emergencyRoomId로 저장되어 있음
        고로 EmergencyRooms에서 HpId로 검색하여 해당하는 emergencyRoomId를 찾아야 함
         */
        Optional<EmergencyRoom> emergencyRoom = emergencyRoomRepository.findByHpId(hpId);
        System.out.println("emergencyRoomId: " + emergencyRoom.orElse(null).getId());
        return emergencyRoom.orElseThrow(() -> NoticeNotFoundHpIdException.EXCEPTION).getId();
    }

    @Transactional
    public List<NoticeReadInfo> readAllById(Long emergencyRoomId) {
        return noticeRepository.findByEmergencyRoomId(emergencyRoomId);
    }

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId) {
        noticeInfoValidator.validateUpdateTitle(updateInfo); // title Null 검사
        noticeInfoValidator.validateUpdateContent(updateInfo); // content Null 검사
        Notice before =
                noticeRepository
                        .findById(noticeId)
                        .orElseThrow(() -> NoticeNotFoundNoticeIdException.EXCEPTION);
        before.updateTitle(String.valueOf(updateInfo.title()));
        before.updateContent(String.valueOf(updateInfo.content()));
        noticeRepository.save(before);
    }
}
