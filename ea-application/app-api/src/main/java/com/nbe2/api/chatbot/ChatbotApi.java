package com.nbe2.api.chatbot;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.chatbot.dto.QuestionRequest;
import com.nbe2.api.chatbot.dto.SessionResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.chatbot.ChatbotService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatbot")
public class ChatbotApi {

    private final ChatbotService chatbotService;

    @PostMapping("/session")
    public Response<SessionResponse> connectChatbot() {
        return Response.success(SessionResponse.of(chatbotService.openChatMemorySession()));
    }

    @PostMapping("/query")
    public Flux<ServerSentEvent<String>> sendQuestion(@RequestBody QuestionRequest request) {
        return Flux.<String>create(
                        emitter ->
                                chatbotService.getResponse(
                                        request.toQuestion(),
                                        new ChatbotService.ResponseHandler() {
                                            @Override
                                            public void onResponse(String response) {
                                                emitter.next(response);
                                            }

                                            @Override
                                            public void onComplete() {
                                                emitter.complete();
                                            }

                                            @Override
                                            public void onError(Throwable throwable) {
                                                emitter.error(throwable);
                                            }
                                        }),
                        FluxSink.OverflowStrategy.BUFFER)
                .map(str -> ServerSentEvent.<String>builder().data(str).build());
    }

    // Postman에서 SSE 스트리밍 응답은 보기가 불편해서
    // 임시로 로컬 테스트 용 API 추가, 추후에 프론트 연결 시 제거할 예정
    @PostMapping("/test")
    public Flux<String> testForLocal(@RequestBody QuestionRequest request) {
        return Flux.create(
                emitter ->
                        chatbotService.getResponse(
                                request.toQuestion(),
                                new ChatbotService.ResponseHandler() {
                                    @Override
                                    public void onResponse(String response) {
                                        emitter.next(response);
                                    }

                                    @Override
                                    public void onComplete() {
                                        emitter.complete();
                                    }

                                    @Override
                                    public void onError(Throwable throwable) {
                                        emitter.error(throwable);
                                    }
                                }),
                FluxSink.OverflowStrategy.BUFFER);
    }

    @DeleteMapping("/session")
    public Response<Void> closeChatbot(@RequestParam String id) {
        chatbotService.closeChatMemorySession(id);
        return Response.success();
    }
}
