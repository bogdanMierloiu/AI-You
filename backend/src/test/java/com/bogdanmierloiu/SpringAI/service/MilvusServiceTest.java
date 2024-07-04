package com.bogdanmierloiu.SpringAI.service;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MilvusServiceTest {

    @Autowired
    private MilvusService milvusService;

    @Autowired
    private OpenAiEmbeddingsService openAiEmbeddingsService;

    @Test
    void testMilvusService() {
        assertNotNull(milvusService);
    }

    @Test
    void save() {
        String text = "Hello, world!";
        float[] embedding = openAiEmbeddingsService.getEmbedding(text);
        assert embedding != null : "Embedding should not be null";
        assert embedding.length > 0 : "Embedding should not be null or empty";
        System.out.println(Arrays.toString(embedding));
        milvusService.saveEmbeddings("Simple save", List.of(embedding));
    }

    @Test
    void addInVectorStore() {
        String content = "Hello, world!";
        String textToEmbedAndAdd = "Hello, Milvus!";
        milvusService.addInVectorStore(content, textToEmbedAndAdd);
    }

    @Test
    void addWithMilvusClient() throws Exception {
        String text = "Hello";
        milvusService.addWithMilvusClient(text);
    }

    @Test
    void embedAndSave() {
        String text = "Hello";
        float[] embedding = openAiEmbeddingsService.getEmbedding(text);
        assert embedding != null;
        milvusService.saveEmbeddings("Hello from test", List.of(embedding));
        List<Document> artificialIntelligence = milvusService.similaritySearch("Artificial Intelligence");
        artificialIntelligence.stream().map(Document::getEmbedding).forEach(System.out::println);
    }


    @Test
    void search() {
        String text = "Hello, Milvus!";
        float[] embedding = openAiEmbeddingsService.getEmbedding(text);
        assert embedding != null;
        System.out.println(Arrays.toString(embedding));
        milvusService.saveEmbeddings("Milvus", List.of(embedding));

        // Search embeddings and verify
        List<Document> documents = milvusService.similaritySearch(text);

        // Ensure we get exactly one document matching the search criteria
        assert documents.size() > 1 : "Expected at least one document";

        Document document = documents.getFirst();
        Object embeddingFromDocument = document.getMetadata().get("embedding");

        // Check that the document has an ID and an embedding
        assert document.getId() != null : "Document ID should not be null";
        assert embeddingFromDocument != null : "Document embedding should not be null or empty";

    }

}