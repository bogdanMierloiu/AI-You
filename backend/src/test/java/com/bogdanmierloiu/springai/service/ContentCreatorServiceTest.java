package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.ContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class ContentCreatorServiceTest {

    @Autowired
    private ContentCreatorService contentCreatorService;


    @Test
    void generateContent() {
        String promptRequest = "similarity search";
        String generatedContent = contentCreatorService.generateContent(promptRequest);
        assert generatedContent != null;
        log.info("Generated content: {}", generatedContent);
    }
}