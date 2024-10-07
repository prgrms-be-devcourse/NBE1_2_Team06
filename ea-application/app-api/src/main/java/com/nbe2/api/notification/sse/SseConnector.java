package com.nbe2.api.notification.sse;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.notification.sse.exception.SseConnectionException;
import com.nbe2.domain.notification.CommentEvent;

@Component
@RequiredArgsConstructor
public class SseConnector {
    public static final long TIMEOUT = Long.MAX_VALUE;

    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter connect(Long userId) {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        sseEmitterRepository.save(userId, emitter);
        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(() -> sseEmitterRepository.remove(userId));

        try {
            emitter.send(
                    SseEmitter.event()
                            .id("")
                            .name(CommentEvent.EVENT_NAME)
                            .data("emitter connected"));
        } catch (IOException e) {
            throw SseConnectionException.EXCEPTION;
        }

        return emitter;
    }
}
