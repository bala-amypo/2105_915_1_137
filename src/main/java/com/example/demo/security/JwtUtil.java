package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMillis = 86400000; // 24 hours
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }
    
    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    public Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }
    
    public Boolean isTokenValid(String token, String username) {
        final String tokenUsername = getUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
    
    public String getUsernameFromToken(String token) {
        return getUsername(token);
    }
    
    public Boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        return isTokenValid(token, userDetails.getUsername());
    }
    
    public long getExpirationMillis() {
        return expirationMillis;
    }
}