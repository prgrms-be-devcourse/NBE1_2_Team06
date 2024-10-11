package com.nbe2.api.notification.sse;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.EventSender;
import com.nbe2.domain.notification.NotificationEvent;

@Component
@RequiredArgsConstructor
public class SseSender implements EventSender {
    private final SseEmitterRepository sseEmitterRepository;

    public void send(NotificationEvent event) {
        Optional<SseEmitter> emitter = sseEmitterRepository.findById(event.targetId());

        if (emitter.isEmpty()) return;

        try {
            emitter.get()
                    .send(
                            SseEmitter.event()
                                    .id("")
                                    .name(event.notificationType().name())
                                    .data(event.referenceUri()));
        } catch (IOException e) {
            sseEmitterRepository.remove(event.targetId());
            emitter.get().completeWithError(e);
        }
    }
}
