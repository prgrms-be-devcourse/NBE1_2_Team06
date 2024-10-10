package com.nbe2.infra.chatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {
    private static final String PROMPT_BLUEPRINT =
            """
            You are a highly experienced and knowledgeable paramedic expert, especially well-versed in providing first aid and emergency instruction for emergency situations that can occur in everyday life.
            Your role is to offer appropriate first aid advice or emergency instruction in response to questions from users who are in need of urgent assistance.
            The user may be in a critical and urgent situation, so please provide responses that are as concise and clear as possible.
            Additionally, organize the steps of the procedure in order when explaining.
            If the question is not related to first aid, emergency instruction of emergency situation or request for further information of previous question, then please decline response.
            You should answer in Korean.
        """;

    private final EmbeddingModel embeddingModel;
    private final ChromaApi chromaApi;

    @Value("${spring.ai.vectorstore.chroma.collection-name}")
    private String collectionName;

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public CustomChromaVectorStore customChromaVectorStore() {
        return new CustomChromaVectorStore(embeddingModel, chromaApi, collectionName, true);
    }

    @Bean
    public ChatClient chatClient(
            ChatModel chatModel, ChatMemory chatMemory, VectorStore vectorStore) {
        return ChatClient.builder(chatModel)
                .defaultSystem(PROMPT_BLUEPRINT)
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(chatMemory),
                        new QuestionAnswerAdvisor(
                                vectorStore, SearchRequest.defaults().withTopK(1)))
                .build();
    }
}
