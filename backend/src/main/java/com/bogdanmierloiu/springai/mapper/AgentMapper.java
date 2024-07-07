package com.bogdanmierloiu.springai.mapper;


import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import com.bogdanmierloiu.springai.dto.agent.TraitDto;
import com.bogdanmierloiu.springai.entity.Agent;
import com.bogdanmierloiu.springai.entity.Trait;

public class AgentMapper {

    private AgentMapper() {
    }

    public static AgentDto mapToAgentDto(Agent agent) {
        return AgentDto.builder()
                .uuid(agent.getUuid())
                .name(agent.getName())
                .language(agent.getLanguage())
                .expertise(agent.getExpertise())
                .tone(agent.getTone())
                .usage(agent.getUsage())
                .createdAt(agent.getCreatedAt())
                .traits(mapToTraitDto(agent.getTrait()))
                .build();
    }

    public static TraitDto mapToTraitDto(Trait trait) {
        return TraitDto.builder()
                .empathy(trait.getEmpathy())
                .reliability(trait.getReliability())
                .confidence(trait.getConfidence())
                .attentionToDetail(trait.getAttentionToDetail())
                .adaptability(trait.getAdaptability())
                .patience(trait.getPatience())
                .communication(trait.getCommunication())
                .innovation(trait.getInnovation())
                .resilience(trait.getResilience())
                .collaboration(trait.getCollaboration())
                .build();
    }

}
