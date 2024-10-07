package com.example.javaEcommerce.utils;

import com.example.javaEcommerce.config.EnvConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Log4j2
public class JwtUtils {

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(EnvConfig.JWT_SECRET.getBytes());
    }

    public static String generateToken(Map<String, Object> params, long expireTime) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + expireTime);
        return Jwts.builder()
                .subject("userInfo")
                .claims(params)
                .issuedAt(now)
                .expiration(expired)
                .signWith(getSecretKey())
                .compact();
    }

    public static boolean tokenIsValid(String token) {
        try {
            Jwts
                    .parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty", ex);
        }
        return false;
    }

    public static String getFromJwt(String token, String key) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return (String) claims.get(key);
    }


}
