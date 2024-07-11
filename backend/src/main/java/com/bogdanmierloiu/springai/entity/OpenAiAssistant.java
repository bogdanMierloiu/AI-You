package com.bogdanmierloiu.springai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record OpenAiAssistant(
        String id,
        String object,

        @JsonProperty("created_at")
        long createdAt,

        String name,
        String description,
        String model,
        String instructions,

        List<Tool> tools,

        @JsonProperty("tool_resources")
        Map<String, ToolResource> toolResources,

        Map<String, String> metadata,

        @JsonProperty("top_p")
        Double topP,

        Double temperature,

        @JsonProperty("response_format")
        String responseFormat
) {
    @Builder
    public record Tool(
            String type) {
    }

    @Builder
    public record ToolResource(

            @JsonProperty("file_ids")
            List<String> fileIds) {
    }
}