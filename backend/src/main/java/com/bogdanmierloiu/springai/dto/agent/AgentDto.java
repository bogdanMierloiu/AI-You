package com.bogdanmierloiu.springai.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "AgentRequest", description = "Agent request object")
@Builder
public record AgentDto(

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

        @Schema(description = "The traits of the agent")
        TraitDto traits

) {
}
