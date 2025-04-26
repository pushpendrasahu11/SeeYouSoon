package com.ps.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes()); // This line converts that string into a secure key using Keys.hmacShaKeyFor(). This key is used to sign and verify JWTs.

    public static String generateToken(Authentication auth){
        String jwt = Jwts.builder()
                .setIssuer("MeetChat")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFormJwt(String jwt){
//        Bearer token

        jwt = jwt.substring(7);    // skipping workd "bearer "

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims);

        String email = String.valueOf(claims.get("email"));

        return email;


    }

}
