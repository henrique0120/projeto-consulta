package br.com.henrique.medicalclinic.config;

import br.com.henrique.medicalclinic.entity.UserEntity;
import br.com.henrique.medicalclinic.repository.UserRepository;
import br.com.henrique.medicalclinic.security.JWTCreator;
import br.com.henrique.medicalclinic.security.JWTObject;
import br.com.henrique.medicalclinic.service.query.impl.LoginQueryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthenticatedUserProvider {

    private final JWTCreator jwtCreator;
    private final UserRepository userRepository;
    private final LoginQueryService service;

    public UserEntity getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT não encontrado");
        }

        String token = authHeader.substring(7);
        JWTObject jwt = jwtCreator.parseToken(token);
        String email = jwt.getSubject();

        return service.verifyUser(email);

    }
}
