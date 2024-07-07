package com.bogdanmierloiu.springai.dto;

import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

@Schema(description = "The user data transfer object")
@Builder
public record UserDto(

        String username,
        String role,
        Set<AgentDto> agents
) {
}
