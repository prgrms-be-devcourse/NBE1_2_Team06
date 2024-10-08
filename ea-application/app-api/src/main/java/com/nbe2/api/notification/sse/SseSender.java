package com.nbe2.api.notification.sse;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.CommentEvent;
import com.nbe2.domain.notification.EventSender;

@Component
@RequiredArgsConstructor
public class SseSender implements EventSender {
    private final SseEmitterRepository sseEmitterRepository;

    public void send(CommentEvent event) {
        Optional<SseEmitter> emitter = sseEmitterRepository.findById(event.getOwner().getId());

        if (emitter.isEmpty()) return;

        try {
            emitter.get()
                    .send(
                            SseEmitter.event()
                                    .id("")
                                    .name(CommentEvent.EVENT_NAME)
                                    .data(event.getPostTitle()));
        } catch (IOException e) {
            sseEmitterRepository.remove(event.getOwner().getId());
        }
    }
}
