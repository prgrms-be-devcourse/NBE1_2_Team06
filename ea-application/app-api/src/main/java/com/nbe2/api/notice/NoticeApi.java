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
            @RequestBody NoticteCreateRequest request, @RequestParam(name = "userId") Long userId) {
        NoticeInfo newNoticeinfo = request.toNoticeInfo();
        noticeService.writeNotice(newNoticeinfo, userId);
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
    public Response<List<NoticeReadResponse>> getNotices(
            @RequestParam(name = "emergecnyRoomId") Long emergecnyRoomId) {
        List<NoticeReadInfo> readInfos = noticeService.readNotice(emergecnyRoomId);
        List<NoticeReadResponse> readResponses =
                readInfos.stream().map(NoticeReadResponse::fromNoticeReadInfo).toList();
        return Response.success(readResponses);
    }
}
