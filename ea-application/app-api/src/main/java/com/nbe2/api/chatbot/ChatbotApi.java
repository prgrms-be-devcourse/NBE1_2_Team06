package com.nbe2.api.chatbot;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.chatbot.dto.QuestionRequest;
import com.nbe2.infra.chatbot.client.OpenAiClient;

import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatbot")
public class ChatbotApi {

    // Flux를 사용해야 해서 인프라 모듈에서 클래스를 냅다 가져와야 함
    private final OpenAiClient chatClient;

    // ServerSentEvent를 응답값으로 보내면 SSE로 전달됨
    // 그래야 원래 의도했던 OpenAI의 생성된 응답을 글자 조각 단위로 스트리밍해줄 수 있음
    @GetMapping("/test")
    public Flux<ServerSentEvent<String>> openaiTest(@RequestBody QuestionRequest request) {
        return chatClient
                .invoke(request.question())
                .map(str -> ServerSentEvent.<String>builder().data(str).build());
    }
}
