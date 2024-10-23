package com.nbe2.api.chatbot;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.chatbot.dto.ChatResponse;
import com.nbe2.api.chatbot.dto.QuestionRequest;
import com.nbe2.api.chatbot.dto.SessionResponse;
import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.chatbot.ChatbotService;

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
    public Response<ChatResponse> askOfEmergencyManual(@RequestBody QuestionRequest request) {
        return Response.success(
                ChatResponse.of(chatbotService.getChatResponse(request.toQuestion())));
    }

    @DeleteMapping("/session")
    public Response<Void> closeChatbot(@RequestParam String id) {
        chatbotService.closeChatMemorySession(id);
        return Response.success();
    }
}
