package io.datajek.springdata.jdbc.banking_system.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "asdfasdfasdkkasdkfaksdflwlelwelwlelwelwelwelwlelwelwle";
    private static final long Expiration_MS = 3600000;

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Expiration_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload();

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getemailFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload().getSubject();

    }

    public String getRoleFromToken(String token) {
        return (String) Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token).getPayload()
                .get("role");
    }
}