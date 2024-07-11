package com.bogdanmierloiu.springai.dto.agent;

import lombok.Builder;

import java.util.List;

@Builder
public record OpenAiAssistantRequest(
        String instructions,
        String name,
        List<Tool> tools,
        String model
) {
    @Builder
    public record Tool(String type) {
    }
}
