package com.lucie.pharmacare.authoriz.AuthorizController;




import com.lucie.pharmacare.authoriz.dto.AuthResponse;
import com.lucie.pharmacare.authoriz.dto.LoginRequest;
import com.lucie.pharmacare.security.JwtService;
import com.lucie.pharmacare.user.entity.Role;
import com.lucie.pharmacare.user.entity.User;
import com.lucie.pharmacare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Check if user exists in DB, if not create them
        userRepository.findByEmail(request.getEmail())
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(
                                        request.getPassword()))
                                .name(request.getEmail())
                                .role(Role.ADMIN)
                                .provider("local")
                                .build()
                ));

        String token = jwtService.generateToken(
                request.getEmail(),
                List.of("ROLE_ADMIN")
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
    @GetMapping("/failure")
    public ResponseEntity<String> failure() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("OAuth2 login failed!");
    }

}


