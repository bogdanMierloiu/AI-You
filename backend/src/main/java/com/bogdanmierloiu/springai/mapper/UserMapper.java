package com.bogdanmierloiu.springai.mapper;

import com.bogdanmierloiu.springai.dto.UserDto;
import com.bogdanmierloiu.springai.entity.User;

import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole().getName())
                .agents(Optional.ofNullable(user.getAgents())
                        .map(agents -> agents.stream()
                                .map(AgentMapper::mapToAgentDto)
                                .collect(Collectors.toSet()))
                        .orElse(null))
                .build();
    }
}
