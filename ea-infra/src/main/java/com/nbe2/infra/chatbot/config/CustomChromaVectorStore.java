package com.nbe2.infra.chatbot.config;

import java.util.*;

import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.ChromaVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.util.Assert;

public class CustomChromaVectorStore extends ChromaVectorStore {
    private final EmbeddingModel embeddingModel;
    private final ChromaApi chromaApi;
    private String collectionId;

    public CustomChromaVectorStore(
            EmbeddingModel embeddingModel,
            ChromaApi chromaApi,
            String collectionName,
            boolean initializeSchema) {
        super(embeddingModel, chromaApi, collectionName, initializeSchema);
        this.embeddingModel = embeddingModel;
        this.chromaApi = chromaApi;
    }

    // ChromaVectorStore의 메서드에서 필터링하는 부분만 제외, 나머지 코드 똑같음
    @Override
    public List<Document> doSimilaritySearch(SearchRequest request) {
        String query = request.getQuery();
        Assert.notNull(query, "Query string must not be null");
        float[] embedding = this.embeddingModel.embed(query);
        ChromaApi.QueryRequest queryRequest =
                new ChromaApi.QueryRequest(embedding, request.getTopK());
        ChromaApi.QueryResponse queryResponse =
                this.chromaApi.queryCollection(this.collectionId, queryRequest);
        List<ChromaApi.Embedding> embeddings =
                this.chromaApi.toEmbeddingResponseList(queryResponse);
        List<Document> responseDocuments = new ArrayList();
        Iterator var10 = embeddings.iterator();

        while (var10.hasNext()) {
            ChromaApi.Embedding chromaEmbedding = (ChromaApi.Embedding) var10.next();
            float distance = chromaEmbedding.distances().floatValue();
            String id = chromaEmbedding.id();
            String content = chromaEmbedding.document();
            Map<String, Object> metadata = chromaEmbedding.metadata();
            if (metadata == null) {
                metadata = new HashMap();
            }

            ((Map) metadata).put("distance", distance);
            Document document = new Document(id, content, (Map) metadata);
            document.setEmbedding(chromaEmbedding.embedding());
            responseDocuments.add(document);
        }

        return responseDocuments;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        this.collectionId = super.getCollectionId();
    }
}
