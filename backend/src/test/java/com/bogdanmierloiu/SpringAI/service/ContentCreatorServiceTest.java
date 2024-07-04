package com.bogdanmierloiu.SpringAI.service;

import com.bogdanmierloiu.SpringAI.dto.ContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
        ContentResponse generatedContent = contentCreatorService.generateContent(promptRequest);
        assert generatedContent != null;
        log.info("Generated content: {}", generatedContent);
    }
}