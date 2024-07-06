package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import com.bogdanmierloiu.springai.dto.agent.TraitDto;
import com.bogdanmierloiu.springai.entity.Agent;
import com.bogdanmierloiu.springai.entity.Trait;
import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.mapper.AgentMapper;
import com.bogdanmierloiu.springai.repo.AgentRepo;
import com.bogdanmierloiu.springai.repo.TraitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepo agentRepo;
    private final TraitRepo traitRepo;
    private final UserService userService;

    private static final String NOT_DEFINED = "not defined";

    public AgentDto createAgent(AgentDto agent) {
        User owner = userService.getAuthenticatedUser();
        int index = owner.getUsername().indexOf('@');
        Trait traits = createTrait(agent.traits());
        Agent newAgent = Agent.builder()
                .name(Optional.ofNullable(agent.name())
                        .orElse(owner.getUsername().substring(0, index) + "'s agent"))
                .language(Optional.ofNullable(agent.language()).orElse("english"))
                .expertise(Optional.ofNullable(agent.expertise()).orElse(NOT_DEFINED))
                .tone(Optional.ofNullable(agent.tone()).orElse("friendly"))
                .usage(Optional.ofNullable(agent.usage()).orElse(NOT_DEFINED))
                .createdAt(LocalDateTime.now())
                .trait(traits)
                .owners(Set.of(owner))
                .build();
        return AgentMapper.mapToAgentDto(agentRepo.save(newAgent));
    }


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


    private Trait createTrait(TraitDto traitDto) {
        return traitRepo.save(Trait.builder()
                .empathy(Optional.ofNullable(traitDto.empathy()).orElse(NOT_DEFINED))
                .reliability(Optional.ofNullable(traitDto.reliability()).orElse(NOT_DEFINED))
                .confidence(Optional.ofNullable(traitDto.confidence()).orElse(NOT_DEFINED))
                .attentionToDetail(Optional.ofNullable(traitDto.attentionToDetail()).orElse(NOT_DEFINED))
                .adaptability(Optional.ofNullable(traitDto.adaptability()).orElse(NOT_DEFINED))
                .patience(Optional.ofNullable(traitDto.patience()).orElse(NOT_DEFINED))
                .communication(Optional.ofNullable(traitDto.communication()).orElse(NOT_DEFINED))
                .innovation(Optional.ofNullable(traitDto.innovation()).orElse(NOT_DEFINED))
                .resilience(Optional.ofNullable(traitDto.resilience()).orElse(NOT_DEFINED))
                .collaboration(Optional.ofNullable(traitDto.collaboration()).orElse(NOT_DEFINED))
                .build());
    }
}
