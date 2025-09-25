package br.com.henrique.medicalclinic.service.query;

import br.com.henrique.medicalclinic.entity.UserEntity;

public interface ILoginQueryService {
    UserEntity verifyUser(final String email);

    void verifyPassword(final String rawPassword, final String encodedPassword, final String email);
}
