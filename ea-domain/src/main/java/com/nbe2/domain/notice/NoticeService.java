package com.nbe2.domain.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataService;
import com.nbe2.domain.global.util.PagingUtil;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeAppender noticeAppender;
    private final NoticeReader noticeReader;
    private final NoticeUpdater noticeUpdater;
    private final NoticeDeleter noticeDeleter;
    private final FileMetaDataService fileMetaDataService;

    /*public Notice writeNotice(NoticeInfo noticeInfo, Long userId) { // Notice 등록
        // @TODO 병원 관계자만 공지사항을 작성할 수 있어야 됨
        //@TODO 수정하기 아래의 메소드(writeNoticeWithFile)로 바꿈
        EmergencyRoom noticeAppenderEmergencyRoom = noticeAppender.getEmergencyRoom(noticeInfo);
        User noticeAppenderUser = noticeAppender.getUser(userId);
        return noticeAppender.append(noticeInfo, noticeAppenderUser, noticeAppenderEmergencyRoom);
    }*/

    @Transactional
    public void writeNoticeWithFile(
            NoticeInfo newNoticeInfo, Long userId, List<Long> fileIds) { // Notice 파일 추가
        List<NoticeFile> noticeFiles = new ArrayList<>();

        Notice newNotice = noticeAppender.createNotice(newNoticeInfo, userId);
        System.out.println("newNoticeInfo content : " + newNoticeInfo.content());
        if (!fileIds.isEmpty()) {
            System.out.println("fileSize : " + fileIds.size());
            for (Long fileId : fileIds) {
                if (fileId != null) {
                    // 파일들을 Notice_files 테이블에 저장
                    FileMetaData fileMetaData = fileMetaDataService.getFileMetaData(fileId);
                    noticeFiles.add(NoticeFile.of(fileMetaData, newNotice));
                }
            }
        }
        newNotice.addFiles(noticeFiles);
        noticeAppender.append(newNotice);
    }

    public void deleteNotice(Long noticeId) {
        noticeDeleter.deleteNotice(noticeId);
    }

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId) {
        noticeUpdater.updateNotice(updateInfo, noticeId);
    }

    @Transactional(readOnly = true)
    public PageResult<NoticeReadInfo> readNotice(Long emergecnyRoomId, Page page) {
        return noticeReader.readAll(PagingUtil.toPageRequest(page), emergecnyRoomId);
    }

    @Transactional(readOnly = true)
    public PageResult<NoticeReadInfo> searchByTitle(Long emergecnyRoomId, String title, Page page) {
        return noticeReader.searchTitle(PagingUtil.toPageRequest(page), emergecnyRoomId, title);
    }
}
