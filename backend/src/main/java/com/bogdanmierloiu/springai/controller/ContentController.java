package com.bogdanmierloiu.springai.controller;

import com.bogdanmierloiu.springai.service.ContentCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/content")
public class ContentController {

    private final ContentCreatorService contentCreatorService;

    @PostMapping
    public ResponseEntity<String> generateContent(@RequestParam String promptRequest) {
        return ResponseEntity.ok(contentCreatorService.generateContent(promptRequest));
    }

}
