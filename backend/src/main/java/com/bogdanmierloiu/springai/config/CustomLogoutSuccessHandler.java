package com.bogdanmierloiu.springai.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

//    private final JwtBlacklistService jwtBlacklist;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = extractJwtFromHeader(request).orElse(null);
        Optional.ofNullable(token)
                .ifPresent(t -> {
//                    jwtBlacklist.addTokenToBlacklist(t);
                    response.setStatus(HttpServletResponse.SC_OK);
                    handleLogoutError(response);
                });
    }

    private Optional<String> extractJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    private void handleLogoutError(HttpServletResponse response) {
        try {
            response.getWriter().write("{\"message\" : \"Logout successful\"}");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
