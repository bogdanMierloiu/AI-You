package com.bogdanmierloiu.springai.controller;

import com.bogdanmierloiu.springai.dto.UserDto;
import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.repo.UserRepo;
import com.bogdanmierloiu.springai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

}
