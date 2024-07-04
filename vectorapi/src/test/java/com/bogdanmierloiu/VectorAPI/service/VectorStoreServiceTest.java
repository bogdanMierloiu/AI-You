package com.bogdanmierloiu.VectorAPI.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class VectorStoreServiceTest {

    @Autowired
    private VectorStoreService vectorStoreService;

    @Test
    void testAdd() {
        vectorStoreService.testAdd();
    }

    @Test
    void testSearch() {
        String query = "Spring";
        List<Document> searchResult = vectorStoreService.testSearch(query);
        log.info("Size: {}", searchResult.size());
        assertFalse(searchResult.isEmpty());
        List<String> extractedResults = searchResult.stream()
                .map(Document::getContent)
                .toList();
        log.info("Extracted results: {}", extractedResults);
    }
}