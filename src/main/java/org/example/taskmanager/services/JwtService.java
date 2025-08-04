package org.example.taskmanager.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.example.taskmanager.config.JwtConfig;
import org.example.taskmanager.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    final JwtConfig jwtConfig;


    public String generateAccessToken(User user) {

        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }
    public String generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    private String generateToken(User user, int tokenExpiration) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("Email", user.getEmail())
                .claim("name", user.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * tokenExpiration))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public Boolean validateToken(String token) {

        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }
        catch (Exception e) {
            return false;
        }

    }

    private Claims getClaims(String token) {
        return  Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public Long getUserIdByToken(String token) {
        try {
            return Long.valueOf(getClaims(token).getSubject());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid token subject: not a numeric user ID", e);
        }
    }

}
