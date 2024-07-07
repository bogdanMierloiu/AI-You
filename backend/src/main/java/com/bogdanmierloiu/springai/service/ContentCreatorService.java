package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.ContentResponse;
import com.bogdanmierloiu.springai.entity.Agent;
import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.prompts.DefaultPrompts;
import com.bogdanmierloiu.springai.repo.AgentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.autoconfigure.openai.OpenAiChatProperties;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentCreatorService {

    private final ChatClient chatClient;

    private final OpenAiChatProperties openAiChatProperties;

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserService userService;
    private final AgentRepo agentRepo;

    public String generateContent(String promptRequest) {
        BeanOutputParser<ContentResponse> parser = new BeanOutputParser<>(ContentResponse.class);
        String format = parser.getFormat();

        OpenAiChatOptions openAiChatOptions = new OpenAiChatOptions.Builder(openAiChatProperties.getOptions())
                .withModel("gpt-4o")
                .withMaxTokens(200)
                .build();

        User authenticatedUser = userService.getAuthenticatedUser();
        Agent agent = agentRepo.findAllByOwnersIsIn(Set.of(authenticatedUser)).stream()
                .filter(agentFromList -> agentFromList.getUuid().equals(UUID.fromString("bedad778-4b8a-4930-81ba-a1edec3a4d20")))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        List<String> similaritySearchResults = getSimilaritySearchResults(promptRequest, agent.getUuid());
        String contentCreatorPrompt = DefaultPrompts.getGenerateContentPrompt(agent, promptRequest, similaritySearchResults);
        log.info("Content creator prompt: {}", contentCreatorPrompt);

        Prompt promptToSend = new Prompt(contentCreatorPrompt, openAiChatOptions);

        ChatResponse call = chatClient.call(promptToSend);
        return call.getResult().getOutput().getContent();
    }

    private List<String> getSimilaritySearchResults(String promptRequest, UUID agentUuid) {
        String baseUrl = "http://localhost:8081/api/vectors";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("query", promptRequest)
                .queryParam("agentId", agentUuid.toString());

        ResponseEntity<List<String>> response = restTemplate.exchange(
                builder.toUriString(),
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get similarity search results" + response.getStatusCode());
        }
    }
}
