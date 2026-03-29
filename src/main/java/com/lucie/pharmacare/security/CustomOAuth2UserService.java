package com.lucie.pharmacare.security;

import com.lucie.pharmacare.user.entity.Role;
import com.lucie.pharmacare.user.entity.User;
import com.lucie.pharmacare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        // 1. Load user info from Google/GitHub
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 2. Get provider name (google or github)
        String provider = userRequest.getClientRegistration()
                .getRegistrationId();

        // 3. Get email from OAuth2 user
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // 4. Find or create user in our database
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isEmpty()) {
            // New user — save to database
            User newUser = User.builder()
                    .email(email)
                    .name(name)
                    .password("")
                    .role(Role.USER) // ← changed from ROLE_USER
                    .provider(provider)
                    .build();
            userRepository.save(newUser);
        }

        return oAuth2User;
    }
}