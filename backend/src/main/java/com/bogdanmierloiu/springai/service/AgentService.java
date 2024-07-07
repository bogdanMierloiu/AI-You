package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import com.bogdanmierloiu.springai.dto.agent.StoreRequest;
import com.bogdanmierloiu.springai.dto.agent.TraitDto;
import com.bogdanmierloiu.springai.entity.Agent;
import com.bogdanmierloiu.springai.entity.Trait;
import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.mapper.AgentMapper;
import com.bogdanmierloiu.springai.repo.AgentRepo;
import com.bogdanmierloiu.springai.repo.TraitRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentService {

    private final AgentRepo agentRepo;
    private final TraitRepo traitRepo;
    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String NOT_DEFINED = "not defined";

    public AgentDto createAgent(AgentDto agent) {
        User owner = userService.getAuthenticatedUser();
        Trait traits = createTrait(agent.traits());
        Agent newAgent = Agent.builder()
                .name(Optional.ofNullable(agent.name())
                        .orElse(generateAgentName(owner)))
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

    private String generateAgentName(User user) {
        if (user.getUsername().contains("@")) {
            int index = user.getUsername().indexOf('@');
            return user.getUsername().substring(0, index) + "'s agent";
        }
        return user.getUsername() + "'s agent";
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

    public void uploadFile(UUID agentUuid, MultipartFile file) {
        Agent agent = agentRepo.findByUuid(agentUuid)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found"));
        StoreRequest storeRequest = StoreRequest.builder()
                .content(fileToString(file))
                .metadata(new HashMap<>(
                        Map.of("agentId", agent.getUuid().toString(),
                                "filename", file.getOriginalFilename())))
                .build();
        uploadInVectorStore(storeRequest);

    }

    private String fileToString(MultipartFile file) {
        byte[] fileContent;
        try {
            fileContent = file.getBytes();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read file content");
        }
        return new String(fileContent);
    }

    private void uploadInVectorStore(StoreRequest storeRequest) {
        try {
            restTemplate.postForObject("http://localhost:8081/api/vectors", storeRequest, Void.class);
        } catch (RestClientException e) {
            log.error("Error uploading file to vector store: {}", e.getMessage());
        }
    }
}
