package com.bogdanmierloiu.springai.controller;

import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.repo.UserRepo;
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

    private final UserRepo userRepo;

    @GetMapping
    public ResponseEntity<User> getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailOrAddress = principal.toString();
        Optional<User> optionalByEmail = userRepo.findByEmail(emailOrAddress);
        return optionalByEmail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(userRepo.findByAddress(emailOrAddress).orElseThrow()));
    }

}
