package com.nbe2.api.notification;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.notification.dto.NotificationDetailResponse;
import com.nbe2.api.notification.dto.UnreadResponse;
import com.nbe2.api.notification.sse.SseConnector;
import com.nbe2.common.annotation.CursorDefault;
import com.nbe2.common.dto.Cursor;
import com.nbe2.common.dto.CursorResult;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.notification.NotificationDetail;
import com.nbe2.domain.notification.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationApi {

    private final NotificationService notificationService;
    private final SseConnector sseConnector;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        SseEmitter emitter = sseConnector.connect(userPrincipal.userId());
        return ResponseEntity.ok(emitter);
    }

    @GetMapping("/my")
    public Response<CursorResult<NotificationDetailResponse>> getNotificationHistory(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @CursorDefault Cursor cursor) {
        CursorResult<NotificationDetail> history =
                notificationService.getNotificationHistory(userPrincipal.userId(), cursor);
        return Response.success(history.map(NotificationDetailResponse::from));
    }

    @GetMapping("/my/unread")
    public Response<UnreadResponse> getUnreadNotificationExistence(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return Response.success(
                UnreadResponse.of(
                        notificationService.hasUnreadNotification(userPrincipal.userId())));
    }
}
