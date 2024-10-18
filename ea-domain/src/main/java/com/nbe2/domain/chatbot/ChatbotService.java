package com.nbe2.domain.chatbot;

import java.util.UUID;

public interface ChatbotService {

    default String openChatMemorySession() {
        return UUID.randomUUID().toString();
    }

    String getChatResponse(Question question);

    void closeChatMemorySession(String sessionId);
}
