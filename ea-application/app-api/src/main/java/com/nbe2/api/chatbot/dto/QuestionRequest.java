package com.nbe2.api.chatbot.dto;

import com.nbe2.domain.chatbot.Question;

public record QuestionRequest(String query, String sessionId) {

    public Question toQuestion() {
        return new Question(query, sessionId);
    }
}
