package com.bogdanmierloiu.springai.service;

import com.bogdanmierloiu.springai.dto.UserDto;
import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.mapper.UserMapper;
import com.bogdanmierloiu.springai.repo.RoleRepo;
import com.bogdanmierloiu.springai.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final Random random;

    @Transactional
    public UserDto getUserProfile() {
        User authenticatedUser = getAuthenticatedUser();
        return UserMapper.mapToUserDto(authenticatedUser);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User getUserByAddress(String address) {
        Optional<User> optionalUser = userRepo.findByAddress(address);
        return optionalUser
                .map(user -> {
                    user.setNonce(generateNonce());
                    return userRepo.save(user);
                })
                .orElseGet(() -> saveUserFromAddress(address));
    }

    private User saveUserFromAddress(String address) {
        String firstPart = address.substring(0, 5);
        String lastPart = address.substring(address.length() - 5);
        return userRepo.save(User.builder()
                .role(roleRepo.findByName("MEMBER"))
                .address(address)
                .nonce(generateNonce())
                .email(firstPart + "...." + lastPart)
                .build());
    }

    private Integer generateNonce() {
        return Math.abs(random.nextInt() * 1000000);
    }

    public User getAuthenticatedUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return username.contains("@")
                ? userRepo.findByEmail(username).orElseThrow()
                : userRepo.findByAddress(username).orElseThrow();
    }
}
