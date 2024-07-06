package com.bogdanmierloiu.springai.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String message,
        String jwt,
        boolean status
) {
}
