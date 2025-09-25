package br.com.henrique.medicalclinic.service.impl;

import br.com.henrique.medicalclinic.controller.request.LoginRequest;
import br.com.henrique.medicalclinic.controller.response.LoginResponse;
import br.com.henrique.medicalclinic.entity.UserEntity;
import br.com.henrique.medicalclinic.security.JWTCreator;
import br.com.henrique.medicalclinic.security.JWTObject;
import br.com.henrique.medicalclinic.service.ILoginService;
import br.com.henrique.medicalclinic.service.query.ILoginQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final ILoginQueryService service;
    private final JWTCreator jwtCreator;

    @Override
    public LoginResponse checkUser(LoginRequest loginRequest) {
        UserEntity user = service.verifyUser(loginRequest.email());
        service.verifyPassword(loginRequest.password(), user.getPassword(), user.getEmail());

        JWTObject jwtObject = new JWTObject();
        jwtObject.setSubject(user.getEmail());
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + 600_000));
        jwtObject.setRoles(user.getRoles());

        LoginResponse sessao = new LoginResponse();
        sessao.setLogin(user.getEmail());
        sessao.setToken(jwtCreator.createToken(jwtObject));

        return sessao;
    }
}
