package com.bogdanmierloiu.VectorAPI.controller;

import com.bogdanmierloiu.VectorAPI.dto.StoreRequest;
import com.bogdanmierloiu.VectorAPI.service.VectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<String>> search(@RequestParam String query) {
        List<String> searchResult = vectorStoreService.search(query);
        return ResponseEntity.ok(searchResult);
    }

}
