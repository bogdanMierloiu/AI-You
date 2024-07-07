package com.bogdanmierloiu.springai.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "AgentRequest", description = "Agent request object")
@Builder
public record AgentDto(

        @Schema(description = "The UUID of the agent")
        UUID uuid,

        @Schema(description = "The name of the agent", example = "Magic Agent")
        String name,

        @Schema(description = "The language of the agent", example = "Spanish", defaultValue = "English")
        String language,

        @Schema(description = "The expertise of the agent", example = "AI, Machine Learning, NLP")
        String expertise,

        @Schema(description = "The tone of the agent", example = "Formal, Informal, Friendly")
        String tone,

        @Schema(description = "The usage of the agent", example = "Customer Support, Sales, Marketing")
        String usage,

        @Schema(description = "The creation date of the agent")
        LocalDateTime createdAt,

        @Schema(description = "The traits of the agent")
        TraitDto traits

) {
}
