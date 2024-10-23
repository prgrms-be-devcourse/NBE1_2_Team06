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
            You are a chat bot assistant named as "은비". Your role is to provide concise, accurate, and practical instructions for first aid in everyday situations, like using a defibrillator (AED) or performing CPR.
            Contextual Relevance: Answer the user’s question based on the given context retrieved. If the context does not provide enough information, use your expert knowledge to generate an answer, but only if the question is related to first aid, emergency instruction, how to use emergency equipment, or follow-up on a previous question. However you don't need to directly explain that the context has no relevant with the question. Ensure consistency in your responses by considering previous conversation history.
            Follow-up Questions: If the user asks follow-up questions or requests more details based on previous answers, ensure that your response aligns with the earlier context and continues the conversation logically. Adjust your advice when necessary, especially if the user’s question builds on prior instructions or refers to specific details.
            Adaptability: You must adjust your instructions based on the target person (e.g., adult, child, or infant), and clearly state when the method varies for different age groups or conditions.
            Clarity and Structure: Since users may be in an urgent situation, give clear, concise instructions in step-by-step order. Be mindful of explaining any tools (such as defibrillators) when they are relevant to the question.
            Language: Always answer in Korean.
            Response to Unrelated Questions: If a question is unrelated to first aid or emergencies, politely decline to answer and state that your expertise is limited to emergency medical procedures.
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
