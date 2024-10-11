package com.nbe2.domain.notice;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataReader;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class NoticeAppender {

    private final UserReader userReader;
    private final NoticeRepository noticeRepository;
    private final NoticeInfoValidator noticeInfoValidator;
    private final EmergencyRoomReader emergencyRoomReader;
    private final FileMetaDataReader fileMetaDataReader;

    public Notice append(Notice newNotice) { // insert
        return noticeRepository.save(newNotice);
    }

    public Notice createNotice(NoticeInfo newNoticeInfo, UserPrincipal userPrincipal) {
        noticeInfoValidator.validateRole(userPrincipal);

        EmergencyRoom emergencyRoom = emergencyRoomReader.read(newNoticeInfo.hpId());
        User user = userReader.read(userPrincipal.userId());
        // 제목, 내용 null 검사
        noticeInfoValidator.validateNull(newNoticeInfo.title(), newNoticeInfo.content());
        return Notice.from(newNoticeInfo, user, emergencyRoom);
    }

    public void addFileIds(Notice newNotice, List<Long> fileIds) {
        if (fileIds.isEmpty()) {
            return;
        }
        List<FileMetaData> files = fileMetaDataReader.readAll(fileIds);
        List<NoticeFile> noticeFiles =
                files.stream().map(fileMetaData -> NoticeFile.of(fileMetaData, newNotice)).toList();
        newNotice.addFiles(noticeFiles);
    }
}
