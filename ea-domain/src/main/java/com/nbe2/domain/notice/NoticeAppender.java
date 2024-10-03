package com.nbe2.domain.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

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
        System.out.println("content : " + newNotice.getContent());
        return noticeRepository.save(newNotice);
    }

    public Notice createNotice(NoticeInfo newNoticeInfo, Long userId) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(newNoticeInfo.hpId());
        User user = userReader.read(userId);
        noticeInfoValidator.validateNotice(newNoticeInfo);
        return Notice.from(newNoticeInfo, user, emergencyRoom);
    }

    public void addFileIds(Notice newNotice, List<Long> fileIds) {
        List<NoticeFile> noticeFiles = new ArrayList<>();
        if (!fileIds.isEmpty()) {
            for (Long fileId : fileIds) {
                if (fileId != null) {
                    // 파일들을 Notice_files 테이블에 저장
                    FileMetaData fileMetaData = fileMetaDataReader.read(fileId);
                    noticeFiles.add(NoticeFile.of(fileMetaData, newNotice));
                }
            }
        }
        newNotice.addFiles(noticeFiles);
    }
}
