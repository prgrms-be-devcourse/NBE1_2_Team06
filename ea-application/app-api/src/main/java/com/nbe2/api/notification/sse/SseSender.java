package com.nbe2.api.notification.sse;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.NewNotificationEvent;
import com.nbe2.domain.notification.NotificationSender;

@Component
@RequiredArgsConstructor
public class SseSender implements NotificationSender {
    private final SseEmitterRepository sseEmitterRepository;

    @Override
    public void send(NewNotificationEvent event) {
        Optional<SseEmitter> emitter = sseEmitterRepository.findById(event.targetId());

        if (emitter.isEmpty()) return;

        try {
            emitter.get()
                    .send(
                            SseEmitter.event()
                                    .id("")
                                    .name(event.type().name())
                                    .data("new notification sent"));
        } catch (IOException e) {
            sseEmitterRepository.remove(event.targetId());
            emitter.get().completeWithError(e);
        }
    }
}
