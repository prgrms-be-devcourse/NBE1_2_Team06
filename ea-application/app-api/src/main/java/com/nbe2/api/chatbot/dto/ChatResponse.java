package com.nbe2.api.chatbot.dto;

public record ChatResponse(String answer) {

    public static ChatResponse of(String answer) {
        return new ChatResponse(answer);
    }
}
