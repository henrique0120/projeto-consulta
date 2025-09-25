package br.com.henrique.medicalclinic.service.query.impl;

import br.com.henrique.medicalclinic.entity.UserEntity;
import br.com.henrique.medicalclinic.exception.InvalidPasswordException;
import br.com.henrique.medicalclinic.exception.UserDontFindException;
import br.com.henrique.medicalclinic.repository.UserRepository;
import br.com.henrique.medicalclinic.service.query.ILoginQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginQueryService implements ILoginQueryService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public UserEntity verifyUser(final String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserDontFindException("Usuário não encontrado"));
    }

    @Override
    public void verifyPassword(final String rawPassword, final String hashedPassword, final String email) {
        boolean passwordOk = encoder.matches(rawPassword, hashedPassword);
        if (!passwordOk) {
            var message = "Senha inválida para o login: " + email;
            throw new InvalidPasswordException(message);
        }
    }
}
