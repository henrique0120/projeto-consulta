package br.com.henrique.medicalclinic.service.query.impl;

import br.com.henrique.medicalclinic.exception.EmailInUseException;
import br.com.henrique.medicalclinic.repository.UserRepository;
import br.com.henrique.medicalclinic.service.query.IRegisterQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterQueryService implements IRegisterQueryService {

    private final UserRepository repository;

    @Override
    public void verifyEmail(final String email){
        if(repository.existsByEmail(email)){
            var message = "O email " + email + " já está em uso";
            throw new EmailInUseException(message);
        }
    }





}
