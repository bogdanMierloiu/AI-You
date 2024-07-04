package com.bogdanmierloiu.SpringAI.VectorAPI.service;

import com.bogdanmierloiu.SpringAI.VectorAPI.entity.Agent;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    public static Agent getDefaultAgent() {
        return Agent.builder()
                .id(1L)
                .name("Default Agent")
                .expertise("programming")
                .personality("funny")
                .language("english")
                .tone("informal")
                .ussage("LinkedIn post")
                .build();
    }
}
