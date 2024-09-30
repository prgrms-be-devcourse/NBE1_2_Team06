package com.nbe2.infra.chatbot.client;

import java.util.List;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

@Component
public class Prompter {

    // @TODO 좀 더 정교한 프롬프팅 작업 필요
    private final String PROMPT_BLUEPRINT =
            """
            You are an emergency care expert with extensive knowledge of first aid methods
            for various situations that may occur in daily life. Please only answer questions
            related to emergency care. Based on the following {context}, please answer the question {query}.
            If the question is not related to emergency care, please say, 'I'm sorry, but I cannot answer that.'
            Please make sure to respond in Korean.
        """;

    // 프롬프트 적용해서 API한테 보낼 쿼리 생성
    public String createPrompt(String query, List<Document> context) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_BLUEPRINT);
        promptTemplate.add("query", query);
        promptTemplate.add("context", context);
        return promptTemplate.render();
    }
}
