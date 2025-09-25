package br.com.henrique.medicalclinic.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTCreator {


    private final JWTConfig jwtConfig;

    private SecretKey secretKey;

    public JWTCreator(JWTConfig config) {
        this.jwtConfig = config;
    }

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtConfig.getKEY().getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(JWTObject jwtObject) {
        return jwtConfig.getPREFIX() + " " + Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim("authorities", checkRoles(jwtObject.getRoles()))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public JWTObject parseToken(String token) {
        token = token.replace(jwtConfig.getPREFIX(), "").trim();

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        JWTObject object = new JWTObject();
        object.setSubject(claims.getSubject());
        object.setIssuedAt(claims.getIssuedAt());
        object.setExpiration(claims.getExpiration());
        object.setRoles((List<String>) claims.get("authorities"));
        return object;
    }

    private List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(role -> "ROLE_" + role.replace("ROLE_", ""))
                .collect(Collectors.toList());
    }
}
