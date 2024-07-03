package com.bogdanmierloiu.SpringAI.service;

import io.milvus.client.MilvusClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MilvusService {

    private final VectorStore vectorStore;

    public void saveEmbeddings(String content, List<float[]> embeddings) {
        List<Document> documents = embeddings.stream()
                .map(embeddingArray -> {
                    Map<String, Object> fields = new HashMap<>();
                    fields.put("embedding", embeddingArray);
                    return new Document(UUID.randomUUID().toString(), content, fields);
                })
                .toList();
        try {
            vectorStore.add(documents);
            documents.forEach(doc -> log.info("Embedding saved successfully: {}", doc.getId()));
        } catch (Exception e) {
            log.error("Failed to save embeddings", e);
        }
    }

    public List<Document> similaritySearch(String query) {
        try {
            return vectorStore.similaritySearch(
                    SearchRequest.defaults()
                            .withQuery(query)
                            .withTopK(4)
                            .withSimilarityThreshold(0.5f));
        } catch (Exception e) {
            log.error("Failed to search embeddings", e);
            return List.of();
        }
    }

}
