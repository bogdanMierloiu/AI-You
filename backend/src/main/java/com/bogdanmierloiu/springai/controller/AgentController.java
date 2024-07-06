package com.bogdanmierloiu.springai.controller;

import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import com.bogdanmierloiu.springai.service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @PostMapping
    @Validated
    public ResponseEntity<AgentDto> createAgent(@Valid @RequestBody AgentDto agent) {
        return ResponseEntity.ok(agentService.createAgent(agent));
    }


}
