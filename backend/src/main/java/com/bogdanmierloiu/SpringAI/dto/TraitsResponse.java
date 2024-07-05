package com.bogdanmierloiu.SpringAI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "TraitsResponse", description = "Response containing the traits of an agent.")
public record TraitsResponse(
        @Schema(description = "Empathy traits", example = "Empathetic, Compassionate, Caring")
        String empathy,

        @Schema(description = "Reliability traits", example = "Reliable, Trustworthy, Consistent")
        String reliability,

        @Schema(description = "Confidence traits", example = "Confident, Assertive, Bold")
        String confidence,

        @Schema(description = "Attention to Detail traits", example = "Detail-Oriented, Meticulous, Precise")
        String attentionToDetail,

        @Schema(description = "Adaptability traits", example = "Adaptable, Flexible, Versatile")
        String adaptability,

        @Schema(description = "Patience traits", example = "Patient, Calm, Composed")
        String patience,

        @Schema(description = "Communication traits", example = "Communicative, Articulate, Clear")
        String communication,

        @Schema(description = "Innovation traits", example = "Innovative, Creative, Original")
        String innovation,

        @Schema(description = "Resilience traits", example = "Resilient, Strong, Persistent")
        String resilience,

        @Schema(description = "Collaboration traits", example = "Collaborative, Team-Oriented, Cooperative")
        String collaboration
) {
}
