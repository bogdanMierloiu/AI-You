package com.bogdanmierloiu.SpringAI.VectorAPI.service;

import com.bogdanmierloiu.SpringAI.VectorAPI.entity.Agent;
import com.bogdanmierloiu.SpringAI.VectorAPI.repo.AgentRepo;
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
                .personality("funny")
                .language("english")
                .tone("informal")
                .usage("LinkedIn post")
                .build();
    }
}
