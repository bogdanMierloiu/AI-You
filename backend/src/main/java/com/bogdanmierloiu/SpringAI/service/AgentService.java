package com.bogdanmierloiu.SpringAI.service;

import com.bogdanmierloiu.SpringAI.entity.Agent;
import com.bogdanmierloiu.SpringAI.repo.AgentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepo agentRepo;


    public static Agent getDefaultAgent() {
        return Agent.builder()
                .id(1L)
                .name("Default Agent")
                .expertise("programming")
                .language("english")
                .tone("informal")
                .usage("LinkedIn post")
                .trait(null)
                .build();
    }
}
