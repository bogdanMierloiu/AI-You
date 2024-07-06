package com.bogdanmierloiu.springai.dto.agent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Schema(name = "TraitRequest", description = "Trait request object")
@Builder
public record TraitDto(

        @Schema(description = "Empathy traits", example = "Empathetic, Compassionate, Caring")
        @Size(max = 128, message = "Empathy traits must be at most 128 characters long")
        String empathy,

        @Size(max = 128, message = "Reliability traits must be at most 128 characters long")
        @Schema(description = "Reliability traits", example = "Reliable, Trustworthy, Consistent")
        String reliability,

        @Size(max = 128, message = "Confidence traits must be at most 128 characters long")
        @Schema(description = "Confidence traits", example = "Confident, Assertive, Bold")
        String confidence,

        @Size(max = 128, message = "Attention to Detail traits must be at most 128 characters long")
        @Schema(description = "Attention to Detail traits", example = "Detail-Oriented, Meticulous, Precise")
        String attentionToDetail,

        @Size(max = 128, message = "Adaptability traits must be at most 128 characters long")
        @Schema(description = "Adaptability traits", example = "Adaptable, Flexible, Versatile")
        String adaptability,

        @Size(max = 128, message = "Patience traits must be at most 128 characters long")
        @Schema(description = "Patience traits", example = "Patient, Calm, Composed")
        String patience,

        @Size(max = 128, message = "Communication traits must be at most 128 characters long")
        @Schema(description = "Communication traits", example = "Communicative, Articulate, Clear")
        String communication,

        @Size(max = 128, message = "Innovation traits must be at most 128 characters long")
        @Schema(description = "Innovation traits", example = "Innovative, Creative, Original")
        String innovation,

        @Size(max = 128, message = "Resilience traits must be at most 128 characters long")
        @Schema(description = "Resilience traits", example = "Resilient, Strong, Persistent")
        String resilience,

        @Size(max = 128, message = "Collaboration traits must be at most 128 characters long")
        @Schema(description = "Collaboration traits", example = "Collaborative, Team-Oriented, Cooperative")
        String collaboration
) {
}