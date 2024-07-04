package com.bogdanmierloiu.SpringAI.service;

import com.alibaba.fastjson.JSON;
import com.bogdanmierloiu.SpringAI.dto.ContentResponse;
import com.bogdanmierloiu.SpringAI.entity.Agent;
import com.bogdanmierloiu.SpringAI.prompts.DefaultPrompts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.autoconfigure.openai.OpenAiChatProperties;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContentCreatorService {

    private final ChatClient chatClient;

    private final OpenAiEmbeddingsService openAiEmbeddingsService;
    private final MilvusService milvusService;

    private final OpenAiChatProperties openAiChatProperties;

    public ContentResponse generateContent(String promptRequest) {
        BeanOutputParser<ContentResponse> parser = new BeanOutputParser<>(ContentResponse.class);
        String format = parser.getFormat();

        OpenAiChatOptions openAiChatOptions = new OpenAiChatOptions.Builder(openAiChatProperties.getOptions())
//                .withTemperature(0.7f)
                .withModel("gpt-4o")
                .withMaxTokens(100)
                .build();

        Agent agent = AgentService.getDefaultAgent();
        String contentCreatorPrompt = DefaultPrompts.getContentCreatorPrompt(agent, promptRequest);
        PromptTemplate promptTemplate = new PromptTemplate(contentCreatorPrompt);

        Prompt prompt = promptTemplate.create(Map.of("format", format));

        Prompt promptToSend = new Prompt(prompt.toString(), openAiChatOptions);

        ChatResponse response = chatClient.call(promptToSend);


        return parser.parse(response.getResult().getOutput().getContent());
    }

    public ChatResponse generateContentWithEmbeddings(String promptRequest) throws JsonProcessingException {
        BeanOutputParser<ContentResponse> parser = new BeanOutputParser<>(ContentResponse.class);
        OpenAiChatOptions openAiChatOptions = new OpenAiChatOptions.Builder(openAiChatProperties.getOptions())
                .withModel("gpt-4o")
                .withMaxTokens(100)
                .build();

        Agent agent = AgentService.getDefaultAgent();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(agent.toString());

        float[] embedding = openAiEmbeddingsService.getEmbedding(jsonString);
        milvusService.saveEmbeddings(agent.getName(), List.of(embedding));

        List<Document> personalityTraits = milvusService.similaritySearch("personality");
        List<String> documents = personalityTraits.stream()
                .map(Document::getContent)
                .toList();

        PromptTemplate promptTemplate = new PromptTemplate("You are a helpful assistant, conversing with a user which needs help with a specific topic." +
                "You will be provided with a set of documents, and you will extract the personality traits of the user, and provide them with the best possible answer." +
                "QUESTION: {promptRequest} \n" +
                "DOCUMENTS: {documents}");

        Prompt prompt = promptTemplate.create(
                Map.of("promptRequest", promptRequest,
                        "documents", String.join("\n", documents)));
        Prompt promptToSend = new Prompt(prompt.toString(), openAiChatOptions);
        return chatClient.call(promptToSend);
    }


}
