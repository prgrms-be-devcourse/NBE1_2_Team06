package com.nbe2.infra.chatbot.client;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataRetriever {

    private final VectorStore vectorStore;

    // 사용자 입력 임베딩 후 벡터 DB에서 유사도 검색
    public List<Document> retrieveVector(String query) {
        return vectorStore.similaritySearch(query);
    }
}
