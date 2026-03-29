package com.lucie.pharmacare.user;

import com.lucie.pharmacare.user.entity.User;
import com.lucie.pharmacare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {

        // 1. Get authentication from Security Context
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        // 2. Get email/username from token
        String email = authentication.getName();

        // 3. Build response from token first
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("roles", authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toList());

        // 4. Try to find in DB — adds more info if found
        userRepository.findByEmail(email).ifPresent(user -> {
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("role", user.getRole());
            response.put("provider", user.getProvider());
        });

        return ResponseEntity.ok(response);
    }
}