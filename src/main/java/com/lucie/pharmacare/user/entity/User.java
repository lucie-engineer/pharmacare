package com.lucie.pharmacare.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String password; // nullable for OAuth2 users

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider; // "local", "google", "github"
}
