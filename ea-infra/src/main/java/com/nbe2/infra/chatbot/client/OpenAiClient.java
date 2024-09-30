package com.nbe2.infra.chatbot.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.chatbot.ChatbotService;
import com.nbe2.domain.chatbot.Question;

import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class OpenAiClient implements ChatbotService {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    @Override
    public void getResponse(Question question, ResponseHandler handler) {
        Flux<String> chatResponse =
                chatClient
                        .prompt()
                        .user(question.query())
                        .advisors(
                                advisorSpec ->
                                        advisorSpec
                                                .param(
                                                        AbstractChatMemoryAdvisor
                                                                .CHAT_MEMORY_CONVERSATION_ID_KEY,
                                                        question.sessionId())
                                                .param(
                                                        AbstractChatMemoryAdvisor
                                                                .CHAT_MEMORY_RETRIEVE_SIZE_KEY,
                                                        100))
                        .stream()
                        .content();

        chatResponse.subscribe(
                handler::onResponse, // 데이터가 있을 때 호출
                handler::onError, // 에러 발생 시 호출
                handler::onComplete // 완료 시 호출
                );
    }

    @Override
    public void closeChatMemorySession(String sessionId) {
        chatMemory.clear(sessionId);
    }
}
