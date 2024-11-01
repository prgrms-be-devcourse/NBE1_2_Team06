package com.nbe2.infra.chatbot.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.chatbot.ChatbotService;
import com.nbe2.domain.chatbot.Question;

@Component
@RequiredArgsConstructor
public class OpenAiClient implements ChatbotService {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    @Override
    public String getChatResponse(Question question) {
        return chatClient
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
                .call()
                .content();
    }

    @Override
    public void closeChatMemorySession(String sessionId) {
        chatMemory.clear(sessionId);
    }
}
