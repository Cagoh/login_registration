package io.github.cagoh.loginregistration_3.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // Generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }
    
    // represent information in the payload
    /*
     * {
		  "sub": "1234567890",       // Subject: Unique identifier for the user
		  "name": "John Doe",        // Full name of the user
		  "email": "john.doe@example.com",  // Email address of the user
		  "role": "admin",           // Role of the user (e.g., admin, user, etc.)
		  "exp": 1672531199          // Expiration time of the token (UNIX timestamp)
		}
     */

    // https://stackoverflow.com/questions/73486900/how-to-fix-parser-is-deprecated-and-setsigningkeyjava-security-key-is-deprec
    // https://javadoc.io/static/io.jsonwebtoken/jjwt-api/0.11.2/index.html?io/jsonwebtoken/JwtParserBuilder.html
    // Get username from JWT token
    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        return username;
    }

    // Validate JWT Token
    public boolean validateToken(String token){
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
    }
}