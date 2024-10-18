package com.nbe2.domain.notice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.global.util.PagingUtil;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeAppender noticeAppender;
    private final NoticeReader noticeReader;
    private final NoticeUpdater noticeUpdater;
    private final NoticeDeleter noticeDeleter;
    private final NoticeEventInnerPublisher noticeEventInnerPublisher;

    @Transactional
    public void writeNoticeWithFile(
            NoticeInfo newNoticeInfo,
            UserPrincipal userPrincipal,
            List<Long> fileIds) { // Notice 파일 추가

        Notice newNotice = noticeAppender.createNotice(newNoticeInfo, userPrincipal);

        noticeAppender.addFileIds(newNotice, fileIds);
        noticeAppender.append(newNotice);
        noticeEventInnerPublisher.publish(newNotice);
    }

    public void deleteNotice(Long noticeId, Long userId) {
        noticeDeleter.deleteNotice(noticeId, userId);
    }

    public void updateNotice(NoticeUpdateInfo updateInfo, Long noticeId, Long userId) {
        noticeUpdater.updateNotice(updateInfo, noticeId, userId);
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
