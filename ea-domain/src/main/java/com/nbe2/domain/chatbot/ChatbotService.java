package com.nbe2.domain.chatbot;

import java.util.UUID;

public interface ChatbotService {

    default String openChatMemorySession() {
        return UUID.randomUUID().toString();
    }

    void getResponse(Question question, ResponseHandler handler);

    void closeChatMemorySession(String sessionId);

    interface ResponseHandler {

        void onResponse(String response);

        void onComplete();

        void onError(Throwable throwable);
    }
}
