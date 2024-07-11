package com.bogdanmierloiu.springai.service;


import com.bogdanmierloiu.springai.entity.OpenAiAssistant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class OpenAiAssistantServiceTest {

    @Autowired
    private OpenAiAssistantService openAiAssistantService;

    @Test
    void createOpenAiAssistant() {
        OpenAiAssistant createdAssistant = openAiAssistantService.createOpenAiAssistant();
        assert createdAssistant != null;
        log.info("Created assistant: {}", createdAssistant);
    }

    @Test
    void getAssistants() {
        List<OpenAiAssistant> openAiAssistants = openAiAssistantService.getOpenAiAssistants();
        assert openAiAssistants != null;
        openAiAssistants.forEach(assistant -> log.info("Assistant: {} \n", assistant));

        String firstAgentId = openAiAssistants.getFirst().id();
        Optional<OpenAiAssistant> openAiAssistant = Optional.ofNullable(openAiAssistantService.getOpenAiAssistant(firstAgentId))
                .orElse(Optional.empty());
        assert openAiAssistant.isPresent();
        log.info("Assistant: {}", openAiAssistant);

        Optional<OpenAiAssistant> notFoundAgent = openAiAssistantService.getOpenAiAssistant("invalid-id");
        assert notFoundAgent.isEmpty();
    }

}
