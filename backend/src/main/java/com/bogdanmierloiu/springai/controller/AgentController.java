package com.bogdanmierloiu.springai.controller;

import com.bogdanmierloiu.springai.dto.agent.AgentDto;
import com.bogdanmierloiu.springai.service.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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

    @PatchMapping("/{agentUuid}/upload")
    public ResponseEntity<String> handleFileUpload(
            @PathVariable UUID agentUuid,
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file");
        }
        agentService.uploadFile(agentUuid, file);
        return ResponseEntity.ok("File uploaded successfully");
    }


}
