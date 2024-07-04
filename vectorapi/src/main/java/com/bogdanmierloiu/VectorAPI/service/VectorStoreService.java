package com.bogdanmierloiu.VectorAPI.service;

import com.bogdanmierloiu.VectorAPI.dto.StoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VectorStoreService {

    private final VectorStore vectorStore;

    public List<String> search(String query, Long agentId) {
        return vectorStore.similaritySearch(
                        SearchRequest.query(query)
                                .withTopK(3)
                                .withFilterExpression("agentId == '" + agentId + "'"))
                .stream()
                .map(Document::getContent)
                .toList();
    }

    public void add(StoreRequest storeRequest) {
        Document document = new Document(storeRequest.content(), storeRequest.metadata());
        vectorStore.add(List.of(document));
    }

    public void testAdd() {
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("user_id", "123456")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));
        vectorStore.add(documents);
    }

    public List<Document> testSearch(String query) {
        return vectorStore.similaritySearch(
                SearchRequest.query(query)
                        .withTopK(3));
    }
}
