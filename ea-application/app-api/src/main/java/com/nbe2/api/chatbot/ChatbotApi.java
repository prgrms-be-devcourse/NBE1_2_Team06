package com.nbe2.api.chatbot;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.chatbot.dto.ChatResponse;
import com.nbe2.api.chatbot.dto.QuestionRequest;
import com.nbe2.api.chatbot.dto.SessionResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.chatbot.ChatbotService;
import com.nbe2.domain.chatbot.Question;

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

    @PostMapping("/sync-query")
    public Response<ChatResponse> testForLocal(@RequestBody QuestionRequest request) {
        return Response.success(
                ChatResponse.of(chatbotService.getSyncResponse(request.toQuestion())));
    }

    @DeleteMapping("/session")
    public Response<Void> closeChatbot(@RequestParam String id) {
        chatbotService.closeChatMemorySession(id);
        return Response.success();
    }

    // 프론트 역량 부족으로 스트리밍 구현 실패;;ㅠㅠ
    // 지우자니 해둔게 아까워서 남겨두고 싶음......
    @GetMapping("/query")
    public Flux<ServerSentEvent<String>> sendQuestion(
            @RequestParam String query, @RequestParam String sessionId) {
        return Flux.<String>create(
                        emitter ->
                                chatbotService.getResponse(
                                        new Question(query, sessionId),
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
}
