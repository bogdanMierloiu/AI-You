package com.bogdanmierloiu.springai.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(description = "The request to store content")
public record StoreRequest(

        @NotBlank
        @Schema(description = "The content to be stored")
        String content,

        @Schema(description = "The metadata of the content")
        Map<String, Object> metadata
) {
}
