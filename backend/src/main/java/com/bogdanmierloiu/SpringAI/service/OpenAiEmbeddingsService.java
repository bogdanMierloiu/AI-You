package com.bogdanmierloiu.SpringAI.service;

import com.bogdanmierloiu.SpringAI.dto.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public float[] getEmbedding(String text) {
        String url = "https://api.openai.com/v1/embeddings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String body = "{\"input\":\"" + text + "\", \"model\":\"text-embedding-ada-002\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<OpenAiResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, OpenAiResponse.class);

        return Objects.requireNonNull(response.getBody()).data().getFirst().embedding();
    }
}
