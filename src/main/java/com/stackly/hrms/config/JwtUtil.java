package com.stackly.hrms.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

//JwtUtil → handles token generation and validation
@Component
public class JwtUtil {

    // Secret key (keep it safe in real projects)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey123";

    // Token validity (1 day)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    //Generate JWT token
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username) // set username
                .issuedAt(new Date()) // token created time
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey()) // sign with secret key
                .compact();
    }

    //Extract username from token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate token
    public boolean isTokenValid(String token, String username) {

        String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    //Check token expiration
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    //Get claims (data inside token)
    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Generate signing key
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}