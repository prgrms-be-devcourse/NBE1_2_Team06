package com.nbe2.api.notice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.notice.dto.NoticeReadResponse;
import com.nbe2.api.notice.dto.NoticeUpdateReqeust;
import com.nbe2.api.notice.dto.NoticteCreateRequest;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.file.FileMetaDataService;
import com.nbe2.domain.notice.NoticeFile;
import com.nbe2.domain.notice.NoticeInfo;
import com.nbe2.domain.notice.NoticeReadInfo;
import com.nbe2.domain.notice.NoticeService;
import com.nbe2.domain.notice.NoticeUpdateInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
public class NoticeApi {
    private final NoticeService noticeService;
    private final FileMetaDataService fileMetaDataService;

    @PostMapping // insert
    public Response<Void> createNotice(
            @RequestBody NoticteCreateRequest request,
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "file", required = false) Long fileId) {
        NoticeInfo newNoticeinfo = request.toNoticeInfo();
        System.out.println("fileId : " + fileId);
        // 파일 Id등록
        if (fileId != null) {
            NoticeFile file =
                    NoticeFile.of(
                            fileMetaDataService.getFileMetaData(fileId),
                            noticeService.writeNotice(newNoticeinfo, userId));
            noticeService.writeNoticeWithFile(file);
        } else {
            noticeService.writeNotice(newNoticeinfo, userId);
        }
        return Response.success();
    }

    @PutMapping("/{noticeId}") // update
    public Response<Void> updateNotice(
            @PathVariable Long noticeId, @RequestBody NoticeUpdateReqeust updateReqeust) {
        NoticeUpdateInfo updateInfo = updateReqeust.toNoticeUpdateInfo();
        noticeService.updateNotice(updateInfo, noticeId);
        return Response.success();
    }

    @DeleteMapping("/{noticeId}") // delete
    public Response<Void> deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return Response.success();
    }

    @GetMapping // read
    public Response<PageResult<NoticeReadResponse>> getNotice(
            @RequestParam(name = "emergencyRoomId") Long emergencyRoomId, @PageDefault Page page) {
        PageResult<NoticeReadInfo> noticeReadPageResult =
                noticeService.readNotice(emergencyRoomId, page);
        return Response.success(noticeReadPageResult.map(NoticeReadResponse::fromNoticeReadInfo));
    }

    @GetMapping("/search/{title}") // searchByTitle
    public Response<PageResult<NoticeReadResponse>> searchNotice(
            @RequestParam(name = "emergencyRoomId") Long emergencyRoomId,
            @PathVariable String title,
            @PageDefault Page page) {
        PageResult<NoticeReadInfo> noticeReadPageResult =
                noticeService.searchByTitle(emergencyRoomId, title, page);
        return Response.success(noticeReadPageResult.map(NoticeReadResponse::fromNoticeReadInfo));
    }
}
