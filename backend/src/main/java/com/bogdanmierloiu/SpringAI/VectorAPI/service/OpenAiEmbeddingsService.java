package com.bogdanmierloiu.SpringAI.VectorAPI.service;

import com.bogdanmierloiu.SpringAI.VectorAPI.dto.OpenAiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiEmbeddingsService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;


    private final RestTemplate restTemplate = new RestTemplate();

    public float[] getEmbedding(String text) {
        String url = "https://api.openai.com/v1/embeddings";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Create a map for the body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("input", text);
        requestBody.put("model", "text-embedding-ada-002");

        try {
            // Convert the map to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(requestBody);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<OpenAiResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, OpenAiResponse.class);

            return Objects.requireNonNull(response.getBody()).data().getFirst().embedding();
        } catch (Exception e) {
            // Handle the exception (logging, rethrowing, etc.)
            log.error("Error in getEmbedding", e);
            throw new RuntimeException("Failed to get embedding", e);
        }
    }

    public List<Float> getEmbeddingList(String inputText) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = String.format("{\"input\": \"%s\", \"model\": \"text-embedding-ada-002\"}", inputText);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/embeddings"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response.body());

        JsonNode embeddingNode = jsonResponse.at("/data/0/embedding");
        return StreamSupport.stream(embeddingNode.spliterator(), false)
                .map(JsonNode::floatValue)
                .toList();
    }

}
