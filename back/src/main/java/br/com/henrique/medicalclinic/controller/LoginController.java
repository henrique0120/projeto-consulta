package br.com.henrique.medicalclinic.controller;

import br.com.henrique.medicalclinic.config.SecurityConfig;
import br.com.henrique.medicalclinic.controller.request.LoginRequest;
import br.com.henrique.medicalclinic.controller.response.LoginResponse;
import br.com.henrique.medicalclinic.mapper.UserMapper;
import br.com.henrique.medicalclinic.repository.UserRepository;
import br.com.henrique.medicalclinic.security.JWTCreator;
import br.com.henrique.medicalclinic.service.impl.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final PasswordEncoder encoder;
    private final SecurityConfig securityConfig;
    private final UserMapper mapper;
    private final UserRepository repository;
    private final LoginService service;
    private final JWTCreator jwtCreator;

    @PostMapping("/login")
    public LoginResponse logar(@RequestBody @Valid final LoginRequest request) {
        return service.checkUser(request);
    }

}
