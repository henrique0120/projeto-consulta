package br.com.henrique.medicalclinic.service;

import br.com.henrique.medicalclinic.entity.UserEntity;

public interface IRegisterService {

    void createUser(final UserEntity entity);

}
