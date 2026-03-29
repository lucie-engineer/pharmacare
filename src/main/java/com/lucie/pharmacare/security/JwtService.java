package com.lucie.pharmacare.security;




import java.util.Date;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    //  Generate token with roles
    public String generateToken(String username, List<String> roles) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Generate token (simple)
    public String generateToken(String username) {
        return generateToken(username, new ArrayList<>());
    }

    // Extract username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    //  Extract roles
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);

        Object roles = claims.get("roles");

        if (roles instanceof List<?>) {
            List<?> list = (List<?>) roles;
            List<String> result = new ArrayList<>();

            for (Object role : list) {
                result.add(role.toString());
            }

            return result;
        }

        return new ArrayList<>();
    }

    //Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Check if token is valid for a specific user
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    //  Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}