package com.bogdanmierloiu.SpringAI.VectorAPI.controller;

import com.bogdanmierloiu.SpringAI.VectorAPI.config.JwtProvider;
import com.bogdanmierloiu.SpringAI.VectorAPI.config.UserDetailsServiceConfig;
import com.bogdanmierloiu.SpringAI.VectorAPI.dto.AuthResponse;
import com.bogdanmierloiu.SpringAI.VectorAPI.dto.LoginRequest;
import com.bogdanmierloiu.SpringAI.VectorAPI.entity.User;
import com.bogdanmierloiu.SpringAI.VectorAPI.service.UserService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserDetailsServiceConfig userDetailsServiceConfig;

    private final JwtProvider jwtProvider;
    private final UserService userService;


    @PostMapping("auth/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = userDetailsServiceConfig.authenticate(loginRequest.username(), loginRequest.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = AuthResponse.builder()
                .message("Success")
                .jwt(token)
                .status(true)
                .build();

        return ResponseEntity.ok().body(authResponse);
    }

    @GetMapping("nonce/{address}")
    public ResponseEntity<Integer> getNonce(@PathVariable String address) {
        User user = userService.getUserByAddress(address);
        return ResponseEntity.ok(user.getNonce());
    }

    @PostMapping("metamask/login")
    public ResponseEntity<AuthResponse> loginWithMetaMask(@RequestParam String address, @RequestParam String signature) {
        Authentication authentication = userDetailsServiceConfig.authenticateMetamask(address, signature);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = AuthResponse.builder()
                .message("Success")
                .jwt(token)
                .status(true)
                .build();

        return ResponseEntity.ok().body(authResponse);
    }

    //TODO -> Add the register method with Bcrypt password encoder

}
