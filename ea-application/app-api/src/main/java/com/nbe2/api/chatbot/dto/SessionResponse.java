package com.nbe2.api.chatbot.dto;

public record SessionResponse(String sessionId) {

    public static SessionResponse of(String sessionId) {
        return new SessionResponse(sessionId);
    }
}
