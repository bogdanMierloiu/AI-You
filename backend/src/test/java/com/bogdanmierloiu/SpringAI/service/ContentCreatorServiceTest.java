package com.bogdanmierloiu.SpringAI.service;

import com.bogdanmierloiu.SpringAI.dto.ContentResponse;
import com.bogdanmierloiu.SpringAI.entity.Agent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ContentCreatorServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ContentCreatorServiceTest.class);

    @Autowired
    private ContentCreatorService contentCreatorService;

    @Autowired
    private MilvusService milvusService;

    @Autowired
    private OpenAiEmbeddingsService openAiEmbeddingsService;

    @Test
    void generateContent() {
        String promptRequest = "Embedding information with OpenAI API";
        ContentResponse generatedContent = contentCreatorService.generateContent(promptRequest);
        assert generatedContent != null;
        log.info("Generated content: {}", generatedContent);
    }

    @Test
    void generateContentWithEmbedding() throws JsonProcessingException {
        String promptRequest = "I need a LinkedIn post about embedding and vector database with OpenAI API";
        ChatResponse generatedContent = contentCreatorService.generateContentWithEmbeddings(promptRequest);
        assert generatedContent != null;
        log.info("Generated content with embeddings: {}", generatedContent);
    }
}