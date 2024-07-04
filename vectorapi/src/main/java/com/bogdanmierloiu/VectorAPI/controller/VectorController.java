package com.bogdanmierloiu.VectorAPI.controller;

import com.bogdanmierloiu.VectorAPI.dto.StoreRequest;
import com.bogdanmierloiu.VectorAPI.service.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vectors")
public class VectorController {

    private final VectorStoreService vectorStoreService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody StoreRequest storeRequest) {
        vectorStoreService.add(storeRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> search(@RequestParam String query, @RequestParam Long agentId) {
        List<String> searchResult = vectorStoreService.search(query, agentId);
        return ResponseEntity.ok(searchResult);
    }

}
