package com.nbe2.api.notice;

import java.util.List;

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
import com.nbe2.domain.notice.NoticeInfo;
import com.nbe2.domain.notice.NoticeReadInfo;
import com.nbe2.domain.notice.NoticeService;
import com.nbe2.domain.notice.NoticeUpdateInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
public class NoticeApi {
    private final NoticeService noticeService;

    @PostMapping // insert
    public Response<Void> createNotice(
            @RequestBody NoticteCreateRequest request,
            @RequestParam("userId") Long userId,
            @RequestParam(name = "file", required = false) List<Long> fileIds) {
        // @TODO 의료 관계자만 등록할 수 있게 해야 함
        NoticeInfo newNoticeinfo = request.toNoticeInfo();
        // 파일 Id등록
        noticeService.writeNoticeWithFile(newNoticeinfo, userId, fileIds);
        return Response.success();
    }

    @PutMapping("/{noticeId}") // update
    public Response<Void> updateNotice( // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 수정 가능하게 해야 됨
            @PathVariable Long noticeId, @RequestBody NoticeUpdateReqeust updateReqeust) {
        NoticeUpdateInfo updateInfo = updateReqeust.toNoticeUpdateInfo();
        noticeService.updateNotice(updateInfo, noticeId);
        return Response.success();
    }

    @DeleteMapping("/{noticeId}") // delete
    public Response<Void> deleteNotice(@PathVariable Long noticeId) {
        // @TODO 권한(동일한 Id로 접속해야 됨)이 있어야 삭제 가능하게 해야 됨
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
