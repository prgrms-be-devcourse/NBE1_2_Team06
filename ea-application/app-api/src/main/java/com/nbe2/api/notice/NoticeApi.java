package com.nbe2.api.notice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.notice.dto.NoticeResponse;
import com.nbe2.domain.notice.NoticeInfo;
import com.nbe2.domain.notice.NoticeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class NoticeApi {
    private final NoticeService noticeService;

    @PostMapping("/notices")
    public ResponseEntity<Long> createNotice(
            @RequestBody NoticeInfo noticeInfo,
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "emergencyRoomId") Long emergencyRoomId) {
        NoticeResponse.from(noticeInfo);
        Long noticeId = noticeService.writeNotice(noticeInfo, userId, emergencyRoomId);
        return ResponseEntity.ok(noticeId);
    }
}
