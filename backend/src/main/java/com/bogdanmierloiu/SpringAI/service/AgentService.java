package com.bogdanmierloiu.SpringAI.service;

import com.bogdanmierloiu.SpringAI.entity.Agent;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    public static Agent getDefaultAgent() {
        return Agent.builder()
                .name("Default Agent")
                .expertise("programming")
                .personality("funny")
                .language("english")
                .tone("informal")
                .ussage("LinkedIn post")
                .build();
    }
}
