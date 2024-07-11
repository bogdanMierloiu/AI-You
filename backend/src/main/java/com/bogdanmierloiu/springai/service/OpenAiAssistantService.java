package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.agent.OpenAiAssistantRequest;
import com.bogdanmierloiu.springai.entity.OpenAiAssistant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiAssistantService {

    private final RestTemplate restTemplate;
    private static final String OPENAI_API_ASSISTANTS_URL = "https://api.openai.com/v1/assistants";

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public OpenAiAssistant createOpenAiAssistant() {
        OpenAiAssistantRequest firstAssistant = OpenAiAssistantRequest.builder()
                .instructions("This is the first assistant")
                .name("First Assistant")
                .tools(List.of(OpenAiAssistantRequest.Tool.builder()
                        .type("code_interpreter")
                        .build()))
                .model("gpt-4o")
                .build();
        HttpEntity<OpenAiAssistantRequest> entity = new HttpEntity<>(firstAssistant, buildHeaders(apiKey));
        ResponseEntity<OpenAiAssistant> response = restTemplate.exchange(
                OPENAI_API_ASSISTANTS_URL,
                HttpMethod.POST,
                entity,
                OpenAiAssistant.class);

        return response.getBody();
    }

    public List<OpenAiAssistant> getOpenAiAssistants() {
        HttpEntity<String> entity = new HttpEntity<>(buildHeaders(apiKey));
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                OPENAI_API_ASSISTANTS_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });

        Map<String, Object> responseBody = response.getBody();
        assert responseBody != null;

        List<OpenAiAssistant> assistants = new ArrayList<>();
        if ("list" .equals(responseBody.get("object"))) {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> assistantMaps = (List<Map<String, Object>>) responseBody.get("data");
            for (Map<String, Object> assistantMap : assistantMaps) {
                OpenAiAssistant assistant = mapper.convertValue(assistantMap, OpenAiAssistant.class);
                assistants.add(assistant);
            }
        }

        return assistants;
    }

    public Optional<OpenAiAssistant> getOpenAiAssistant(String assistantId) {
        HttpEntity<String> entity = new HttpEntity<>(buildHeaders(apiKey));
        try {
            ResponseEntity<OpenAiAssistant> response = restTemplate.exchange(
                    OPENAI_API_ASSISTANTS_URL + "/" + assistantId,
                    HttpMethod.GET,
                    entity,
                    OpenAiAssistant.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            log.error("Error getting assistant with id: {}", assistantId, e);
            return Optional.empty();
        }
    }

    private static HttpHeaders buildHeaders(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v2");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
