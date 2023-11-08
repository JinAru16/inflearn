package com.hello.security.securityintro.config;

import com.hello.security.securityintro.security.domain.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtConfig {

    private final SecurityConfig config;

    public String generateJwtToken(UserDetailsImpl user) {
        return generateTokenFromUsername(user, true);
    }

    public String generateJwtRefreshToken(UserDetailsImpl user) {
        return generateTokenFromUsername(user, false);
    }


    public String generateTokenFromUsername(UserDetailsImpl user, boolean isAccessToken){

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ((isAccessToken ? config.getAccessTokenExpirationMin() : config.getRefreshTokenExpirationMin())  * 60000)))
                .claim("username", user.getUsername())
                .claim("phoneNum", user.getPhoneNumber())
                .claim("role", user.getRole())
                .signWith(config.getSecretKey())
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7, token.length());
        }

        return Jwts.parser().setSigningKey(config.getSecretKeyPlain()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) throws Exception {

        if (authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7, authToken.length());
        }

        try {
            Jwts.parser().setSigningKey( config.getSecretKeyPlain() ).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new Exception("JWT Invalid JWT signature : " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new Exception("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new Exception("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new Exception("JWT token is unsupported :" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new Exception("JWT claims string is empty : " + e.getMessage());
        }

    }
}
