package com.nbe2.infra.chatbot.client;

import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class OpenAiClient {

    private final StreamingChatModel chatModel;
    private final DataRetriever dataRetriever;
    private final Prompter prompter;

    // OpenAI API(gpt-4o-mini) 응답 받기
    // ChatModel API를 쓸 때 이전 대화 기록을 기억할 수 없어 좀 아쉬움
    // ChatClient를 직접 쓰면 ChatMemory 적용할 수 있는데 어제 에러 장난 아니게 남
    public Flux<String> invoke(String query) {
        return chatModel.stream(prompter.createPrompt(query, dataRetriever.retrieveVector(query)));
    }
}
