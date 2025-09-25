package br.com.henrique.medicalclinic.service.impl;

import br.com.henrique.medicalclinic.entity.UserEntity;
import br.com.henrique.medicalclinic.repository.UserRepository;
import br.com.henrique.medicalclinic.service.IRegisterService;
import br.com.henrique.medicalclinic.service.query.IRegisterQueryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService implements IRegisterService {
    private final UserRepository repository;
    private final IRegisterQueryService service;
    private final PasswordEncoder encoder;

    @Override
    public void createUser(UserEntity user){
        service.verifyEmail(user.getEmail());
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }

}
