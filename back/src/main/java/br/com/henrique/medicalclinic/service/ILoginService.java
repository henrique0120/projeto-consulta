package br.com.henrique.medicalclinic.service;

import br.com.henrique.medicalclinic.controller.request.LoginRequest;
import br.com.henrique.medicalclinic.controller.response.LoginResponse;

public interface ILoginService {

    LoginResponse checkUser(final LoginRequest loginRequest);

}
