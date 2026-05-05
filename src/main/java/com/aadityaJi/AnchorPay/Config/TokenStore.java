package com.aadityaJi.AnchorPay.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenStore {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private Key key ;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String email){
        String jwtToken = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key)
                .compact();
        storeToken(jwtToken,email);
        return jwtToken;
    }
    public void storeToken(String token,String email){
        redisTemplate.opsForValue().set(token,email,expiration, TimeUnit.MILLISECONDS);
    }

    public boolean validateToken(String token){
        try {
            String email = redisTemplate.opsForValue().get(token);

            if (email == null) {
                return false;
            }

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            String emailFromToken = claimsJws.getBody().getSubject();
            if(email.equals(emailFromToken))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteToken(String token){
        redisTemplate.delete(token);
    }
}
