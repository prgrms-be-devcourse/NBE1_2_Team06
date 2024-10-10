package com.nbe2.api.notification.sse;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SseConnector {
    public static final String CONNECTION_NAME = "CONNECT";
    public static final long TIMEOUT = 30 * 60 * 1000L;

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter connect(Long userId) {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        sseEmitterRepository.save(userId, emitter);
        emitter.onTimeout(() -> sseEmitterRepository.remove(userId));
        emitter.onCompletion(() -> sseEmitterRepository.remove(userId));

        try {
            emitter.send(SseEmitter.event().id("").name(CONNECTION_NAME).data("emitter connected"));
        } catch (IOException e) {
            sseEmitterRepository.remove(userId);
            emitter.completeWithError(e);
        }

        return emitter;
    }
}
