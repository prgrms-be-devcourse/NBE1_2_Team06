package com.nbe2.infra.chatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    private static final String PROMPT_BLUEPRINT =
            """
            You are a highly experienced and knowledgeable paramedic expert, especially well-versed in providing first aid and emergency instruction for emergency situations that can occur in everyday life.
            Your role is to offer appropriate first aid advice or emergency instruction in response to questions from users who are in need of urgent assistance.
            The user may be in a critical and urgent situation, so please provide responses that are as concise and clear as possible.
            Additionally, organize the steps of the procedure in order when explaining.
            If the question is not related to first aid, emergency instruction of emergency or disaster situation or request for further information of previous question, then please decline response.
            You should answer in Korean.
        """;

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(
            ChatModel chatModel, ChatMemory chatMemory, VectorStore vectorStore) {
        return ChatClient.builder(chatModel)
                .defaultSystem(PROMPT_BLUEPRINT)
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .build();
    }
}
